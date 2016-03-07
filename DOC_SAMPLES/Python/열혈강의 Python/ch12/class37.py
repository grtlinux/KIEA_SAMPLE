# class37.py
class A(object): 
    def save(self): 
        print 'A save called' 
 
class B(A): 
    def save(self): 
        print 'B save called' 
        super(B, self).save() 
 
class C(A): 
    def save(self): 
        print 'C save called' 
        super(C, self).save() 
 
class D(B, C): 
    def save(self): 
        print 'D save called' 
        super(D, self).save() 
 
d = D() 
d.save() 
