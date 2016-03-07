# class25.py
class Base: 
    def f(self): 
        self.g()                # 함수 g() 호출 
    def g(self): 
        print 'Base' 
 
class Derived(Base): 
    def g(self):                # 파생 클래스의 함수 g() 
        print 'Derived' 
 
b = Base() 
b.f()           # Base의 g() 호출 
 
a = Derived() 
a.f()           # Derived의 g() 호출
