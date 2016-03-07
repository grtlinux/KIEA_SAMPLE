# class03.py
class Var: 
    c_mem = 100         # 클래스 멤버 정의 
    def f(self): 
        self.i_mem = 200        # 인스턴스 멤버 정의 
    def g(self): 
        print self.i_mem 
        print self.c_mem
