# class32.py
class A: 
        pass 
 
class B: 
        def f(self): 
                pass 
 
class C(B): 
        pass 
         
def check(obj): 
        print obj, '=>', 
        if issubclass(obj, A): print 'A', 
        if issubclass(obj, B): print 'B', 
        if issubclass(obj, C): print 'C', 
        print 
         
check(A) 
check(B) 
check(C) 
__main__.A => A 
__main__.B => B 
__main__.C => B C 
