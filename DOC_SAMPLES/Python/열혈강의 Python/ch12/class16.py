# class16.py
class D(object): 
        def __init__(self): 
                self.__degree = 0 
        def get_degree(self): 	# degree를 읽을 때 호출되는 메써드
                return self.__degree 
        def set_degree(self, d): 	# degree에 기록할 때 호출되는 메써드
                self.__degree = d % 360 
        degree = property(get_degree, set_degree) # degree가 속성으로 사용될 이름

d = D() 
d.degree = 10 
print d.degree 
d.degree = 370 
print d.degree 
d.degree = -370 
print d.degree
