# class36.py
class A(object): 
    def save(self): 
        print 'A save called' 

class B(A): 
    def _save(self): 
        print 'B save called' 
    def save(self): 
        self._save() 
        A.save(self) 

class C(A): 
    def _save(self): 
        print 'C save called' 
    def save(self): 
        self._save() 
        A,save(self) 

class D(B, C): 
    def _save(self): 
        print 'D save called' 
    def save(self): 
        self._save() 
        B._save(self) 
        C._save(self) 
        A.save(self) 

d = D() 
d.save()
