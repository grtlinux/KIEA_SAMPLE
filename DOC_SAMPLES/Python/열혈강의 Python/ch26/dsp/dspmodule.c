/*
Python DSP Module --
	Extension of Numpy which uses arrayobject

Copyright (c) 1999 Gang Seong Lee, gslee@mail.gwu.ac.kr

*/
#include <stdio.h>
#include <string.h>
#include <math.h>

#include "Python.h"
#include "arrayobject.h"


typedef void (DotFunction) Py_FPROTO((char *, int, char *, int, char *, int));

/* Rather than build a generic inner product, this is just dot product. */

#define DOT_PRODUCT(name, number) \
	static void name(char *ip1, int is1, char *ip2, int is2, double *op, int n) { \
	double tmp=(double)0.0;  int i; \
	for(i=0;i<n;i++,ip1+=is1,ip2+=is2) { \
    tmp += *((number *)ip1) * *((number *)ip2); \
} \
	*op = tmp; \
}

DOT_PRODUCT(SHORT_DotProduct, short)
DOT_PRODUCT(INT_DotProduct, int)
DOT_PRODUCT(LONG_DotProduct, long)
DOT_PRODUCT(FLOAT_DotProduct, float)
DOT_PRODUCT(DOUBLE_DotProduct, double)

#define CDOT_PRODUCT(name, number) \
	static void name(char *ip1, int is1, char *ip2, int is2, char *op, int n) { \
	number tmpr=(number)0.0, tmpi=(number)0.0;  int i; \
	for(i=0;i<n;i++,ip1+=is1,ip2+=is2) { \
    tmpr += ((number *)ip1)[0] * ((number *)ip2)[0] - ((number *)ip1)[1] * ((number *)ip2)[1]; \
    tmpi += ((number *)ip1)[1] * ((number *)ip2)[0] + ((number *)ip1)[0] * ((number *)ip2)[1]; \
} \
	((number *)op)[0] = tmpr; ((number *)op)[1] = tmpi;\
}

CDOT_PRODUCT(CFLOAT_DotProduct, float)
CDOT_PRODUCT(CDOUBLE_DotProduct, double)

static void OBJECT_DotProduct(char *ip1, int is1, char *ip2, int is2,
							  char *op, int n) {
	int i;
	PyObject *tmp1, *tmp2, *tmp;
	for(i=0;i<n;i++,ip1+=is2,ip2+=is1) {
		tmp1 = PyNumber_Multiply(*((PyObject **)ip1), *((PyObject **)ip2));
		if (i == 0) {
			tmp = tmp1;
		} else {
			tmp2 = PyNumber_Add(tmp, tmp1);
			Py_XDECREF(tmp);
			tmp = tmp2;
			Py_XDECREF(tmp1);
		}
	}
	Py_XDECREF(*((PyObject **)op));
	*((PyObject **)op) = tmp;
}

static double lpc(double* alph, double* rc, double* r, int n)
{
	int minc, ip, ib, mh;
	double res, s, at;	 /* res - residual */

	rc[1] = -r[1] / r[0];
	alph[0] = 1.0;
	alph[1] = rc[1];
	res = r[0] + r[1] * rc[1];
	for (minc = 2; minc <= n; minc++) {
		s = 0;
		for (ip = 0; ip < minc; ip++)
			s += r[minc - ip] * alph[ip];
		rc[minc] = -s / res;
		mh = minc / 2 + 1;
		for (ip = 1, ib = minc - ip; ip < mh; ip++, ib--) {
			at = alph[ip] + rc[minc] * alph[ib];
			alph[ib] += rc[minc] * alph[ip];
			alph[ip] = at;
		}
		alph[minc] = rc[minc];
		res += rc[minc] * s;
		if (res <= 0) {
			fprintf(stderr, "ERROR : res value=%f\n", res);
			fprintf(stderr, "  debug info : minc=%d rc[minc]=%e s=%e\n", minc, rc[minc], s);
			return(res);
		}
	}
	return res;
}

static void lpcep(double alph[], double errp, double ceps[], int m_p, int m_np)
{
	int i, k;
	double sum;

	ceps[0] = 0.5 * log(errp);
	ceps[1] = -alph[1];
	for (i = 2; i <= m_p; i++) {
		sum = 0;
		for (k = 1; k < i; k++)
			sum += k * ceps[k] * alph[i - k];
		sum /= i;
		ceps[i] = -(alph[i] + sum);
	}
	for (i = m_p + 1; i <= m_np; i++) {
		ceps[i] = 0;
		for (k = 1; k <= m_p; k++)
			ceps[i] -= (i - k) * ceps[i - k] * alph[k];
		ceps[i] /= i;
	}
}	/* end of function cepstrum */

static PyObject *MultiArrayError;
static DotFunction *matrixMultiplyFunctions[] = {NULL,NULL,NULL,SHORT_DotProduct,INT_DotProduct,LONG_DotProduct,
FLOAT_DotProduct,DOUBLE_DotProduct,
CFLOAT_DotProduct, CDOUBLE_DotProduct, OBJECT_DotProduct};


extern PyObject *PyArray_Correlatation(PyObject *op1, PyObject *op2, int Lag, int No) {
	PyArrayObject *ap1, *ap2, *ret;
	int i, n1, n2, n, n12;
	int typenum;
	int is1, is2, os;
	char *ip1, *ip2, *op;
	DotFunction *dot;

	typenum = PyArray_ObjectType(op1, 0);
	typenum = PyArray_ObjectType(op2, typenum);

	ret = NULL;
	ap1 = (PyArrayObject *)PyArray_ContiguousFromObject(op1, typenum, 1, 1);
	if (ap1 == NULL) return NULL;
	ap2 = (PyArrayObject *)PyArray_ContiguousFromObject(op2, typenum, 1, 1);
	if (ap2 == NULL) goto fail;

	n1 = ap1->dimensions[ap1->nd-1];
	n2 = ap2->dimensions[ap2->nd-1];

	if (n1 < n2) { ret = ap1; ap1 = ap2; ap2 = ret; ret = NULL; i = n1;n1=n2;n2=i;}
	n1 -= Lag;
	if (!No)
		No = n1;
	else if (No > n1)
		No = n1;
	n = n2;
	n12 = n1 - n2;

	ret = (PyArrayObject *)PyArray_FromDims(1, &No, PyArray_DOUBLE);
	if (ret == NULL) goto fail;


	dot = matrixMultiplyFunctions[typenum];
	if (dot == NULL) {
		PyErr_SetString(PyExc_ValueError,
			"correlate not available for this type");
		goto fail;
	}

	/* is : item size, op : object pointer, os : object size */
	is1 = ap1->strides[ap1->nd-1];
	is2 = ap2->strides[ap2->nd-1];
	op = ret->data;
	os = ret->descr->elsize;

	ip1 = ap1->data + Lag * is1;
	ip2 = ap2->data;
	for(i=0; i < n12 && i < No; i++) {
		dot(ip1, is1, ip2, is2, op, n);
		ip1 += is1;
		op += os;
	}
	for(; i < n1 && i < No; i++) {
		dot(ip1, is1, ip2, is2, op, n);
		n--;
		ip1 += is1;
		op += os;
	}
	Py_DECREF(ap1);
	Py_DECREF(ap2);
	return PyArray_Return(ret);

fail:
	Py_XDECREF(ap1);
	Py_XDECREF(ap2);
	Py_XDECREF(ret);
	return NULL;
}

#define SUMMATION(name, number) \
	static double name(char *ip1, int is1, int n) { \
	double tmp=(number)0.0;  int i; \
	for(i=0;i<n;i++,ip1+=is1) { \
           tmp += *((number *)ip1); \
        } \
	return tmp; \
}

SUMMATION(SHORT_SUMMATION, short)
SUMMATION(INT_SUMMATION, int)
SUMMATION(LONG_SUMMATION, long)
SUMMATION(FLOAT_SUMMATION, float)
SUMMATION(DOUBLE_SUMMATION, double)

typedef double (SumFunction) Py_FPROTO((char *, int, int));

static SumFunction *SummationFunctions[] = {NULL,NULL,NULL,SHORT_SUMMATION,INT_SUMMATION,LONG_SUMMATION,
FLOAT_SUMMATION,DOUBLE_SUMMATION,
NULL, NULL, NULL};

#define IMAT(m,i,j) (*((int*)((m->data)+(i)*(m->strides[0]))+(j)))
#define LMAT(m,i,j) (*((long*)((m->data)+(i)*(m->strides[0]))+(j)))
#define FMAT(m,i,j) (*((float*)((m->data)+(i)*(m->strides[0]))+(j)))
#define DMAT(m,i,j) (*((double*)((m->data)+(i)*(m->strides[0]))+(j)))

extern PyObject *PyArray_VTLNlinear(PyObject *a, double ratio, double edge) {
	PyArrayObject *ap1, *ret;
	int typenum, no, i, is1;
	int nd, *dim, m, n;
	int Lower_coeffY0, Lower_coeffY1, frameX, coeffX, frameN, coeffN;
	double yedge, alpha0, alpha1, X0, X1, Y0, Y1, z, b;

	typenum = PyArray_ObjectType(a, 0);
	if (typenum != PyArray_FLOAT && typenum != PyArray_DOUBLE) {
		fprintf(stderr, "# ERROR : Filter - only Float32 or Float64 is supported\n");
		return NULL;
	}
	ap1 = (PyArrayObject *)PyArray_ContiguousFromObject(a, typenum, 0, 0);
	if (ap1 == NULL) return NULL;
	nd = ap1->nd;
	/* currently only two dim is supported */
	if (nd != 2)
		goto fail;
	ret = (PyArrayObject *)PyArray_FromDims(nd, ap1->dimensions, typenum);
	if (ret == NULL) goto fail;
	frameN = ap1->dimensions[0];
	coeffN = ap1->dimensions[1];

	/* here we go */
	yedge = edge < ratio ? edge / ratio : 1.0;
	b = yedge < 1.0 ? (1.0 - edge) / (1.0 - yedge) : 0.0;
	if (typenum == PyArray_FLOAT) {
		for (frameX = 0; frameX < frameN; frameX++) {
			for (coeffX = 0; coeffX < coeffN; coeffX++) {
				Y0 = (double)coeffX / coeffN;
				Y1 = (double)(coeffX+1.0) / coeffN;
				X0 = ((Y0 < yedge) ? (ratio * Y0) :
                                        (b * Y0 +  1.0 - b)) * coeffN;
				X1 = ((Y1 < yedge) ? (ratio * Y1) :
                                        (b * Y1 +  1.0 - b)) * coeffN;

				Lower_coeffY1 = (int)(X1);
				alpha1        = X1 - Lower_coeffY1;

				Lower_coeffY0 = (int)(X0);
				alpha0        = (int)(X0) + 1 - X0;
				z             =  0.0;

				if ( Lower_coeffY0 == Lower_coeffY1) {
					z += (X1-X0) * FMAT(ap1, frameX, Lower_coeffY0);
				} else {
					z += alpha0 * FMAT(ap1, frameX, Lower_coeffY0);
					for ( i = Lower_coeffY0+1; i < Lower_coeffY1; i++)
						z += FMAT(ap1, frameX, i);
					if ( Lower_coeffY1 < coeffN)
						z += alpha1 * FMAT(ap1, frameX, Lower_coeffY1);
				}
				FMAT(ret, frameX, coeffX) = z;
			}
		}
	} else {
		for (frameX = 0; frameX < frameN; frameX++) {
			for (coeffX = 0; coeffX < coeffN; coeffX++) {
				Y0 = (double)coeffX / coeffN;
				Y1 = (double)(coeffX+1.0) / coeffN;
				X0 = ((Y0 < yedge) ? (ratio * Y0) :
                                        (b * Y0 +  1.0 - b)) * coeffN;
				X1 = ((Y1 < yedge) ? (ratio * Y1) :
                                        (b * Y1 +  1.0 - b)) * coeffN;

				Lower_coeffY1 = (int)(X1);
				alpha1        = X1 - Lower_coeffY1;

				Lower_coeffY0 = (int)(X0);
				alpha0        = (int)(X0) + 1 - X0;
				z             =  0.0;

				if ( Lower_coeffY0 == Lower_coeffY1) {
					z += (X1-X0) * DMAT(ap1, frameX, Lower_coeffY0);
				} else {
					z += alpha0 * DMAT(ap1, frameX, Lower_coeffY0);
					for ( i = Lower_coeffY0+1; i < Lower_coeffY1; i++)
						z += DMAT(ap1, frameX, i);
					if ( Lower_coeffY1 < coeffN)
						z += alpha1 * DMAT(ap1, frameX, Lower_coeffY1);
				}
				DMAT(ret, frameX, coeffX) = z;
			}
		}
	}
	Py_DECREF(ap1);
	return PyArray_Return(ret);
fail:
	Py_XDECREF(ap1);
	Py_XDECREF(ret);
	return NULL;
}

extern PyObject *PyArray_ReadPcm(char* fname, long no, long startpos, int mean) {
	PyArrayObject *ret;
	int n;
	FILE *fp;

	if ((fp = fopen(fname, "rb")) == NULL) {
		fprintf(stderr, "# ERROR file open error (%s)\n", fname);
		return NULL;
	}
	startpos = startpos / 2 * 2;
	fseek(fp, 0L, SEEK_END);
	if (!no)
		no = (ftell(fp) - startpos) / 2;
	fseek(fp, startpos, SEEK_SET);
	/* make sample array */
	n = no;
	ret = (PyArrayObject *)PyArray_FromDims(1, &n, PyArray_SHORT);
	if (ret == NULL) goto fail;

	/* here we go */
	fread(ret->data, 2, no, fp);
	fclose(fp);
	if (mean) {
		long sum = 0;
		int i;
		short *data = (short*)ret->data;
		for (i = 0; i < no; i++)
			sum += data[i];
		sum /= no;	/* average */
		for (i = 0; i < no; i++)
			data[i] -= sum;
	}
	return PyArray_Return(ret);
fail:
	Py_XDECREF(ret);
	return NULL;
}


extern PyObject *PyArray_Filter(PyObject *a0, PyObject *a1, int pad) {
	PyArrayObject *ret, *arr;
	PyObject *list;
	int typenum, nd;
	int frameN, coeffN, offset;
	int frameX, coeffX, frameY, i, filterN;
	double sum, filter[50];

	typenum = PyArray_ObjectType(a0, 0);
	if (typenum != PyArray_FLOAT && typenum != PyArray_DOUBLE) {
		fprintf(stderr, "# ERROR : Filter - only Float32 or Float64 is supported\n");
		return NULL;
	}
	arr = (PyArrayObject *)PyArray_ContiguousFromObject(a0, typenum, 0, 0);
	if (arr == NULL) return NULL;
	nd = arr->nd;
	/* currently only two dim is supported */
	if (nd != 2)
		goto fail;
	ret = (PyArrayObject *)PyArray_FromDims(nd, arr->dimensions, typenum);
	if (ret == NULL) goto fail;

	/* init */
	frameN = arr->dimensions[0];
	coeffN = arr->dimensions[1];
	/* [offset, [filter_coeffs]] */
	offset = PyInt_AS_LONG(PyList_GetItem(a1, 0));
	list = PyList_GetItem(a1, 1);
	if (list == NULL) {
		fprintf(stderr, "# ERROR : second element is not a list");
		return NULL;
	}
	filterN = PyList_Size(list);
	for (i = 0; i < filterN; i++)
		filter[i] = PyFloat_AsDouble(PyList_GetItem(list, i));

	/* here we go */
	if (typenum == PyArray_FLOAT) goto data_float;
	if (typenum == PyArray_DOUBLE) goto data_double;
data_float:
	for (coeffX = 0; coeffX < coeffN; coeffX++) {
		for (frameX = 0; frameX < frameN; frameX++) {
			sum = 0.0;
			for (i = 0; i < filterN; i++) {
				frameY = frameX - i - offset;
				if (pad != 0) {
					sum += frameY < 0  ? filter[i] * FMAT(arr, 0, coeffX) :
					    frameY >= frameN ? filter[i] * FMAT(arr, frameX-1, coeffX) :
							filter[i] * FMAT(arr, frameY, coeffX);
				} else if (0 <= frameY && frameY < frameN)
					sum += filter[i] * FMAT(arr, frameY, coeffX);
			}
			FMAT(ret, frameX, coeffX) = sum;
		}
	}
	goto final;
data_double:
	for (coeffX = 0; coeffX < coeffN; coeffX++) {
		for (frameX = 0; frameX < frameN; frameX++) {
			sum = 0.0;
			for (i = 0; i < filterN; i++) {
				frameY = frameX - i - offset;
				if (pad != 0) {
					sum += frameY < 0  ? filter[i] * DMAT(arr, 0, coeffX) :
					    frameY >= frameN ? filter[i] * DMAT(arr, frameN-1, coeffX) :
							filter[i] * DMAT(arr, frameY, coeffX);
				} else if (0 <= frameY && frameY < frameN)
					sum += filter[i] * DMAT(arr, frameY, coeffX);
			}
			DMAT(ret, frameX, coeffX) = sum;
		}
	}
final:
	Py_DECREF(arr);
	return PyArray_Return(ret);
fail:
	Py_XDECREF(arr);
	Py_XDECREF(list);
	Py_XDECREF(ret);
	return NULL;
}


#define SVEC(m,i) (*((short*)(m->data)+(i)))
#define IVEC(m,i) (*((int*)(m->data)+(i)))
#define FVEC(m,i) (*((float*)(m->data)+(i)))
#define DVEC(m,i) (*((double*)(m->data)+(i)))
extern PyObject *PyArray_ZeroCrossingRate(PyObject *a0, double fs, int winsize, int mean) {
	PyArrayObject *arr;
	PyObject *list, *ret;
	int i, count, typenum, nd, sampleN, old, new;
	float zcr;
	double sum, norm;

	typenum = PyArray_ObjectType(a0, 0);
	if (typenum != PyArray_SHORT) {
		fprintf(stderr, "# ERROR : ZeroCrossingRate - only Int16 is supported\n");
		return NULL;
	}
	arr = (PyArrayObject *)PyArray_ContiguousFromObject(a0, typenum, 0, 0);
	if (arr == NULL) return NULL;
	/* currently only one dim is supported */
	if (arr->nd != 1)
		goto fail;
	count = 0;
	if (winsize > arr->dimensions[0]) {
		fprintf(stderr, "# ERROR : ZeroCrossingRate - array size(%d) is too small compare to the window size given(%d).\n", arr->dimensions[0], winsize);
		goto fail;
	}
	old = ((SVEC(arr, 0)-mean) >= 0) ? 1 : 0;
	for (i = 1; i < winsize; i++) {
		new = ((SVEC(arr, i) - mean) >= 0) ? 1 : 0;
		count += old ^ new;
		old = new;
	}
	zcr = count * fs / 1000.0 / winsize;
	Py_XDECREF(arr);
	return Py_BuildValue("f", zcr);
fail:
	Py_XDECREF(arr);
	return NULL;
}

extern PyObject *PyArray_GetPointer(PyObject *a0) {
	PyArrayObject *ap1;
	PyObject* ret;
	int typenum;
	int n;

	typenum = PyArray_ObjectType(a0, 0);
	ap1 = (PyArrayObject *)PyArray_ContiguousFromObject(a0, typenum, 1, 1);
	if (ap1 == NULL) return NULL;

	ret = PyInt_FromLong ((long)ap1->data) ;

	return ret;
}

extern PyObject *PyArray_PowerSpec(PyObject *a0, double offset, int half) {
	PyArrayObject *arr, *ret;
	int typenum;
	int i, j, n;
	double *ap, *resp, pw, x, y;

	typenum = PyArray_ObjectType(a0, 0);
	if (typenum != PyArray_CDOUBLE) return NULL;
	arr = (PyArrayObject *)PyArray_ContiguousFromObject(a0, typenum, 1, 1);
	if (arr == NULL) return NULL;
	/* currently only one dim is supported */
	if (arr->nd != 1)
		goto fail;
	n = arr->dimensions[0];
	if (half) n = n/2+1;
	ret = (PyArrayObject *)PyArray_FromDims(1, &n, PyArray_DOUBLE);
	if (ret == NULL) goto fail;

	ap = (double*)arr->data;
	resp = (double*)ret->data;
	for (i = 0, j = 0; j < n; j++) {
		x = ap[i++];
		y = ap[i++];
		resp[j] = x * x + y * y;
		if (resp[j] <= 0.0) resp[j] = offset;
	}

	Py_XDECREF(arr);
	return PyArray_Return(ret);
fail:
	Py_XDECREF(arr);
	return NULL;
}

extern PyObject *PyArray_LogPowerSpec(PyObject *a0, double offset, int half) {
	PyArrayObject *arr, *ret;
	int typenum;
	int i, j, n;
	double *ap, *resp, pw, x, y;

	typenum = PyArray_ObjectType(a0, 0);
	if (typenum != PyArray_CDOUBLE) return NULL;
	arr = (PyArrayObject *)PyArray_ContiguousFromObject(a0, typenum, 1, 1);
	if (arr == NULL) return NULL;
	/* currently only one dim is supported */
	if (arr->nd != 1)
		goto fail;
	n = arr->dimensions[0];
	if (half) n = n/2+1;
	ret = (PyArrayObject *)PyArray_FromDims(1, &n, PyArray_DOUBLE);
	if (ret == NULL) goto fail;

	ap = (double*)arr->data;
	resp = (double*)ret->data;
	for (i = 0, j = 0; j < n; j++) {
		x = ap[i++];
		y = ap[i++];
		pw = x * x + y * y;
		if (pw <= 0.0) pw = offset;
		resp[j] = 10.0 * log10(pw);
	}

	Py_XDECREF(arr);
	return PyArray_Return(ret);
fail:
	Py_XDECREF(arr);
	return NULL;
}

extern PyObject *PyArray_Preemph(PyObject *a0, double coef, PyObject *a1) {
	PyArrayObject *arr, *ret;
	int typenum;
	int i, j, n;
	double *ap, *resp, pw, x, y;

	typenum = PyArray_ObjectType(a0, 0);
	if (typenum != PyArray_DOUBLE) return NULL;
	arr = (PyArrayObject *)PyArray_ContiguousFromObject(a0, typenum, 1, 1);
	if (arr == NULL) return NULL;
	/* currently only one dim is supported */
	if (arr->nd != 1)
		goto fail;
	n = arr->dimensions[0];

	if (a0 == a1) {
		ret = arr;
	} else if (a1 == NULL) {
		ret = (PyArrayObject *)PyArray_FromDims(1, &n, PyArray_DOUBLE);
		if (ret == NULL) goto fail;
	} else {
		typenum = PyArray_ObjectType(a1, 0);
		if (typenum != PyArray_DOUBLE) goto fail;
		ret = (PyArrayObject *)PyArray_ContiguousFromObject(a1, typenum, 1, 1);
		if (ret == NULL) goto fail;
		if (n != ret->dimensions[0]) goto fail;
	}

	ap = (double*)arr->data;
	resp = (double*)ret->data;
	for (i = 1, j = 0; i < n; i++, j++) {
		resp[j] = ap[i] + coef * ap[j];
	}
	resp[n-1] = 0.0;

	if (a1 && a0 != a1) {
		Py_XDECREF(ret);
		return PyArray_Return(NULL);
	}
	if (a1 != a0) Py_XDECREF(arr);
	return PyArray_Return(ret);
fail:
	Py_XDECREF(arr);
	return NULL;
}

extern PyObject *PyArray_Lpc(PyObject *a0) {
	PyArrayObject *arr, *ret;
	int typenum;
	int n;
	double *r, *alph, rc[20];
	double res;

	typenum = PyArray_ObjectType(a0, 0);
	if (typenum != PyArray_DOUBLE) return NULL;
	arr = (PyArrayObject *)PyArray_ContiguousFromObject(a0, typenum, 1, 1);
	if (arr == NULL) return NULL;
	/* currently only one dim is supported */
	if (arr->nd != 1)
		goto fail;
	n = arr->dimensions[0];

	ret = (PyArrayObject *)PyArray_FromDims(1, &n, PyArray_DOUBLE);
	if (ret == NULL) goto fail;

	r = (double*)arr->data;
	alph = (double*)ret->data;

	res = lpc(alph, rc, r, n-1);
	if (res <= 0) {
		Py_XDECREF(arr);
		PyErr_SetString(PyExc_RuntimeError, "LPC error");
		return NULL;
	}
	Py_XDECREF(arr);
	return PyArray_Return(ret);

fail:
	Py_XDECREF(arr);
	return NULL;
}

extern PyObject *PyArray_Lpccepstrum(PyObject *a0, int p) {
	PyArrayObject *arr, *ret;
	int typenum;
	int n;
	double *r, *ceps, rc[20], alph[20];
	double res;

	typenum = PyArray_ObjectType(a0, 0);
	if (typenum != PyArray_DOUBLE) return NULL;
	arr = (PyArrayObject *)PyArray_ContiguousFromObject(a0, typenum, 1, 1);
	if (arr == NULL) return NULL;
	/* currently only one dim is supported */
	if (arr->nd != 1)
		goto fail;
	n = arr->dimensions[0];

	if (p == 0) p = n;
	ret = (PyArrayObject *)PyArray_FromDims(1, &p, PyArray_DOUBLE);
	p--;
	if (ret == NULL) goto fail;

	r = (double*)arr->data;
	ceps = (double*)ret->data;

	res = lpc(alph, rc, r, n-1);
	if (res <= 0) {
		Py_XDECREF(arr);
		PyErr_SetString(PyExc_RuntimeError, "LPC error");
		return NULL;
	}
	lpcep(alph, res, ceps, n-1, p);
	Py_XDECREF(arr);
	return PyArray_Return(ret);

fail:
	Py_XDECREF(arr);
	return NULL;
}


static char doc_correlate[] = "correlate(a,b)";
static PyObject *array_correlate(PyObject *dummy, PyObject *args) {
	PyObject *a1, *a0;
	int lag=0, no=0;

	if (PyArg_ParseTuple(args, "OO|ii", &a0, &a1, &lag, &no) == NULL) return NULL;

	return PyArray_Correlatation(a0, a1, lag, no);
}

static char doc_VTLNlinear[] = "VTLNlinear(a, ratio=1.0, edge=1.0) : a should be Float32 or Float64";
static PyObject *array_VTLNlinear(PyObject *dummy, PyObject *args) {
	PyObject *a0;
	double ratio=1.0, edge=1.0;

	if (PyArg_ParseTuple(args, "O|dd", &a0, &ratio, &edge) == NULL) return NULL;

	return PyArray_VTLNlinear(a0, ratio, edge);
}

static char doc_readpcm[] = "ReadPcm(fname, no=0, startpos=0, mean=0)";
static PyObject *array_ReadPcm(PyObject *dummy, PyObject *args) {
	char *fname;
	int mean=0;
	long no=0, startpos=0;

	if (PyArg_ParseTuple(args, "s|lli", &fname, &no, &startpos, &mean) == NULL) return NULL;

	return PyArray_ReadPcm(fname, no, startpos, mean);
}

static char doc_filter[] = "Filter(array, filter_coeff_list)";
static PyObject *array_Filter(PyObject *dummy, PyObject *args) {
	PyObject *a0, *a1;
	int pad=0;

	if (PyArg_ParseTuple(args, "OO|i", &a0, &a1, &pad) == NULL) return NULL;

	return PyArray_Filter(a0, a1, pad);
}

static char doc_zcr[] = "ZeroCrossingRate(samples, fs, winsize, mean=0)";
static PyObject *array_ZeroCrossingRate(PyObject *dummy, PyObject *args) {
	PyObject *a0;
	int winsize, mean=0;
	double fs;

	if (PyArg_ParseTuple(args, "Odi|i", &a0, &fs, &winsize, &mean) == NULL) return NULL;

	return PyArray_ZeroCrossingRate(a0, fs, winsize, mean);
}

static char doc_getpointer[] = "getpointer(array)";
static PyObject *array_GetPointer(PyObject *dummy, PyObject *args) {
	PyObject *a0;

	if (PyArg_ParseTuple(args, "O", &a0) == NULL) return NULL;
	return PyArray_GetPointer(a0);
}

static char doc_powerspec[] = "powerspec(complex_array)";
static PyObject *array_PowerSpec(PyObject *dummy, PyObject *args) {
	PyObject *a0;
	double d = 0.0;
	int half = 0;

	if (PyArg_ParseTuple(args, "O|di", &a0, &d, &half) == NULL) return NULL;
	return PyArray_PowerSpec(a0, d, half);
}

static char doc_logpowerspec[] = "logpowerspec(complex_array)";
static PyObject *array_LogPowerSpec(PyObject *dummy, PyObject *args) {
	PyObject *a0;
	double d = 0.0;
	int half = 0;

	if (PyArg_ParseTuple(args, "O|di", &a0, &d, &half) == NULL) return NULL;
	return PyArray_LogPowerSpec(a0, d, half);
}

static char doc_preemph[] = "preemph(double_array)";
static PyObject *array_Preemph(PyObject *dummy, PyObject *args) {
	PyObject *a0, *a1=NULL;
	double d = -0.95;

	if (PyArg_ParseTuple(args, "O|dO", &a0, &d, &a1) == NULL) return NULL;
	return PyArray_Preemph(a0, d, a1);
}

static char doc_lpc[] = "lpc(double_array)";
static PyObject *array_Lpc(PyObject *dummy, PyObject *args) {
	PyObject *a0;

	if (PyArg_ParseTuple(args, "O", &a0) == NULL) return NULL;
	return PyArray_Lpc(a0);
}

static char doc_lpccepstrum[] = "lpccepstrum(double_array)";
static PyObject *array_Lpccepstrum(PyObject *dummy, PyObject *args) {
	PyObject *a0;
	int p = 0;

	if (PyArg_ParseTuple(args, "O|i", &a0, &p) == NULL) return NULL;
	return PyArray_Lpccepstrum(a0, p);
}

static struct PyMethodDef dsp_module_methods[] = {
	{"correlate", array_correlate, 1, doc_correlate},
	{"VTLNlinear", array_VTLNlinear, 1, doc_VTLNlinear},
	{"ReadPcm", array_ReadPcm, 1, doc_readpcm},
	{"Filter", array_Filter, 1, doc_filter},
	{"ZeroCrossingRate", array_ZeroCrossingRate, 1, doc_zcr},
	{"getpointer", array_GetPointer, 1, doc_getpointer},
	{"powerspec", array_PowerSpec, 1, doc_powerspec},
	{"logpowerspec", array_LogPowerSpec, 1, doc_logpowerspec},
	{"preemph", array_Preemph, 1, doc_preemph},
	{"lpc", array_Lpc, 1, doc_lpc},
	{"lpccepstrum", array_Lpccepstrum, 1, doc_lpccepstrum},

	{NULL,		NULL, 0}		/* sentinel */
};

/* Initialization function for the module (*must* be called initArray) */

void initdsp() {
	PyObject *m;

	/* Create the module and add the functions */
	m = Py_InitModule("dsp", dsp_module_methods);

	/* Import the array object */
	import_array();

	/* Check for errors */
	if (PyErr_Occurred())
		Py_FatalError("can't initialize module array");
}
