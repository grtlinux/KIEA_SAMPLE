# class15.py
def check(func): 
        if callable(func): 
                print 'callable' 
        else: 
                print 'not callable' 
class B: 
        def func(self, v): 
                return v 
class A:  
        def __call__(self, v):  
                return v 
a = A() 
b = B() 
check(a) 
check(b) 
