# class35.py
class A(object): 
    def save(self): 
        print 'A save called' 
 
class B(A): 
    def save(self): 
        print 'B save called' 
        A.save(self) 
 
class C(A): 
    def save(self): 
        print 'C save called' 
        A.save(self) 
 
class D(B, C): 
    def save(self): 
        print 'D save called' 
        B.save(self) 
        C.save(self) 
