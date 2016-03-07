#include "Python.h"

static PyObject *ErrorObject;

/* calling Python function example */
static PyObject* sample_lower(PyObject *self, PyObject *args)
{
	PyObject *pmod, *pfunc, *pargs, *pstr;
	char *cstr;

    if (!PyArg_ParseTuple(args, "s", &cstr))
        return NULL; 
	pmod = PyImport_ImportModule("string");
	pfunc = PyObject_GetAttrString(pmod, "lower");
	pargs = Py_BuildValue("(s)", cstr);

	pstr = PyEval_CallObject(pfunc, args);

	PyArg_Parse(pstr, "s", &cstr);
	printf("%s\n", cstr);

	Py_DECREF(pmod);
	Py_DECREF(pstr);
	Py_DECREF(pfunc);
	Py_DECREF(pargs);
	Py_INCREF(Py_None);
	return Py_None;
}

static PyObject* sample_dictest(PyObject *self, PyObject *args)
{
	PyObject* dic;
	int len;

    if (!PyArg_ParseTuple(args, "O", &dic))
        return NULL; 
	if (!PyDict_Check(dic)) {
		PyErr_SetString(ErrorObject, "my exception"); 
		return NULL;
	}
	len = PyDict_Size(dic);
	printf("Yes, this is dictionary of len %d\n", len);
	Py_INCREF(Py_None);
	return Py_None; 
}

static PyObject* sample_frange(PyObject *self, PyObject *args)
{
	PyObject* flist;
    double v, from, to, step;
	int size, k;

    if (!PyArg_ParseTuple(args, "ddd", &from, &to, &step))
        return NULL; 
	size = (to-from) / step;
	flist = PyList_New(size);
	v = from;
	for (k = 0; k < size; k++) {
		PyList_SetItem(flist, k, PyFloat_FromDouble(v));
		v += step;
	}
    return flist;
}

static PyObject* sample_system(PyObject *self, PyObject *args)
{
	char *command;
    int sts;
    if (!PyArg_ParseTuple(args, "s", &command))
        return NULL;    
    sts = system(command);
    return Py_BuildValue("i", sts);
}

static struct PyMethodDef sample_methods[] = {
 {"system",       sample_system,    METH_VARARGS}, /* name, address */
 {"frange",       sample_frange,    METH_VARARGS},
 {"dictest",      sample_dictest,   METH_VARARGS},
 {"lower",        sample_lower,     METH_VARARGS},
 {NULL,         NULL}                              /* end, for initmodule */
};

void initsample()
{
    PyObject *m;
    /* 모듈을 생성하고 함수를 등록한다 */
    m = Py_InitModule("sample", sample_methods);        /* registration hook */
	/* 기타의 초기화 처리 */
	ErrorObject = Py_BuildValue("s", "sample error");
	/* ... */
}
