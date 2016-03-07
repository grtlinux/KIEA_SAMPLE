# class23.py
class A: 
    def save(self): 	# A 정보를 저장한다
        print 'A save called' 
 
class B(A): 
    pass 
 
class C(A): 
    def save(self): 	# A 대신 C 정보를 저장한다
        print 'C save called' 
 
class D(B, C): 
    pass 
 
d = D() 
d.save()
