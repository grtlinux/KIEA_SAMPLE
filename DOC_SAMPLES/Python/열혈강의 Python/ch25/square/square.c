/*///////////////////////////////////////////////////////
squaretype.c
주어진 인덱스의 제곱을 넘기는 간단한 클래스를 정의한다.
사용법은 다음과 같다.

import square

s = square.Square(10)
for k in s:
    print k
print len(s)
print s.middle()

__getitem__, __len__, middle 메써드를 정의한다.

이강성	2002.1
///////////////////////////////////////////////////////*/

#include "Python.h"

static PyObject *ErrorObject;   
#define onError(message) \
       { PyErr_SetString(ErrorObject, message); return NULL; }


/*///////////////////////////////////////////////////////
              Square 형 정보들
///////////////////////////////////////////////////////*/

typedef struct {        /* Square 인스턴스 객체 */
    PyObject_HEAD       /* 파이썬 헤더 : 레퍼런스 카운트와 형정보를 갖는다 */
    int limit;			/* 인스턴스 객체 멤버를 여기부터 선언한다 */
} squareobject;

staticforward PyTypeObject Squaretype;	/* 뒤에서 정의된다는 뜻임 */

#define is_squareobject(v)  ((v)->ob_type == &Squaretype)


/*///////////////////////////////////////////////////////
              추가의 함수들
///////////////////////////////////////////////////////*/

static PyObject* square_middle(squareobject* self, PyObject* args)
{
    if (!PyArg_ParseTuple(args, ""))
        return NULL;
    return Py_BuildValue("i", self->limit / 2);
}

static struct PyMethodDef square_inst_methods[] = {     /* 추가의 메써드들 */
 {"middle",       square_middle,     METH_VARARGS, "middle point"},
 {NULL,         NULL,	0, NULL}		/* 마지막 표시 */
};

/*///////////////////////////////////////////////////////
              기본 연산 함수들
///////////////////////////////////////////////////////*/

static squareobject* newsquareobject(int limit)
{
    squareobject *self;
    self = PyObject_NEW(squareobject, &Squaretype); /* squareobject 객체 생성 */
    if (self == NULL)	/* 객체 생성에 실패하면 예외 발생 */
        return NULL;
    self->limit = limit; /* 생성자에 전달된 인수 처리 */
    return self;		/* 새로운 squareobject 형 리턴 */
}

static void square_dealloc(squareobject* self)
{
    PyObject_Del(self);
}

static PyObject* square_getattr(squareobject* self, char* name)
{
    return Py_FindMethod(square_inst_methods, (PyObject *)self, name);
}


/*///////////////////////////////////////////////////////
              시퀀스 자료형 메써드들
///////////////////////////////////////////////////////*/

static int square_length(squareobject* self)
{
    return self->limit;		/* 길이 정보는 정수를 그냥 넘기면 된다 */
}

static PyObject* square_getitem(squareobject* self, int index)
{  
    if (index < 0 || index >= self->limit) { 
        PyErr_SetString(PyExc_IndexError, "index out-of-bounds"); 
        return NULL;
    } 
    return Py_BuildValue("i", index * index);
}

static PyObject* square_slice(squareobject* self, int low, int high)
{
    onError("슬라이싱은 아직 구현되지 않았습니다")
}


/*///////////////////////////////////////////////////////
              자료형의 종류에 따른 메써드들
///////////////////////////////////////////////////////*/

static PySequenceMethods square_sequence = {
      (inquiry)       square_length,            /* len(x)   */
      (binaryfunc)    0,                        /* x + y    */
      (intargfunc)    0,                        /* x * n    */
      (intargfunc)    square_getitem,           /* x[i], in */
      (intintargfunc) square_slice,             /* x[i:j]   */
      (intobjargproc)     0,                    /* x[i] = v */
      (intintobjargproc)  0,                    /* x[i:j]=v */
      (objobjproc) 0,                           /* in */
      /* Added in release 2.0 */
      (binaryfunc) 0,
      (intargfunc) 0
};

static PyTypeObject Squaretype = {      
      /* 타입 헤더 */                    /* 모든 인스턴스가 공유한다 */
      PyObject_HEAD_INIT(&PyType_Type)     
      0,                               /* ob_size */
      "Square",                        /* tp_name */
      sizeof(squareobject),            /* tp_basicsize */
      0,                               /* tp_itemsize */

      /* 표준 메써드들 */
      (destructor)  square_dealloc,	/* tp_dealloc */
      (printfunc)   0,				/* tp_print */
      (getattrfunc) square_getattr,	/* tp_getattr */
      (setattrfunc) 0,				/* tp_setattr */
      (cmpfunc)     0,				/* tp_compare */
      (reprfunc)    0,				/* tp_repr */

      /* 자료형 종류 */
      0,                             /* 수치형 메써드들 */
      &square_sequence,              /* 시퀀스형 메써드들 */
      0,                             /* 매핑형 메써드들 */

      /* 기타 메써드들 */
      (hashfunc)   0,                /* tp_hash */
      (binaryfunc) 0,                /* tp_call */
      (reprfunc)   0,                /* tp_str  */

};  /* 추가의 다른 메써드들은 object.h 참조 */


/*/////////////////////*/
/* 모듈 단위 작업들    */
/*/////////////////////*/
static PyObject* square_new(PyObject *self, PyObject *args) /* 생성자 */
{
    int limit;
    if (!PyArg_ParseTuple(args, "i", &limit)) /* args는 생성자 인수 */
        return NULL;
    return (PyObject *)newsquareobject(limit); /* 인스턴스 객체를 넘겨준다 */
} 

static struct PyMethodDef square_methods[] = {
    {"Square",  square_new,  METH_VARARGS, "Create new Square object"},	/* 클래스 이름과 함수 주소 */
    {NULL,     NULL,	0,	NULL}              
};

void initsquare(void)
{
    PyObject *m, *d;
	Squaretype.ob_type = &PyType_Type;
    m = Py_InitModule("square", square_methods);   /* 모듈 초기화 */

    d = PyModule_GetDict(m);	/* 모듈의 이름공간을 가져온다 */
    ErrorObject = Py_BuildValue("s", "square error");	/* 에러 객체를 만든다 */
    PyDict_SetItemString(d, "error", ErrorObject); /* 에러 객체를 이름 공간에 등록한다 */
    if (PyErr_Occurred())
        Py_FatalError("can't initialize module square");
}
