# class31.py
class A: 
        pass 
 
class B: 
        def f(self): 
                pass 
 
class C(B): 
        pass 
         
def check(obj): 
        print obj, '=>', 
        if isinstance(obj, A): print 'A', 
        if isinstance(obj, B): print 'B', 
        if isinstance(obj, C): print 'C', 
        print 
         
a = A() 
b = B() 
c = C() 
 
check(a) 
check(b) 
check(c) 
