# class27.py
import UserDict 
 
class MyDict(UserDict.UserDict): 
    def __init__(self, data={}, **kw): 
        UserDict.UserDict.__init__(self)  # 베이스 클래스의 생성자를 호출. 데이터 초기화 
        self.update(data)       # 데이터 추가 
        self.update(kw) # 데이터 추가 
    def keys(self): 
        ks = self.data.keys() 
        ks.sort() 
        return ks 
 
a = MyDict(a=1, b=2, c=3, d=4) 
print a 
print a.keys()
