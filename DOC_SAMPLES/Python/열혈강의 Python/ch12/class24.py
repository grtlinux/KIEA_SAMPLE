# class24.py
class A(object): 	# object로부터 상속 받았다
    def save(self): 
        print 'A save called' 
 
class B(A): 
    pass 
 
class C(A): 
    def save(self): 
        print 'C save called' 
 
class D(B, C): 
    pass 
 
d = D() 
d.save()
