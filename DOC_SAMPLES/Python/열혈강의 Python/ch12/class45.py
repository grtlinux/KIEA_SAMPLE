# class45.py
class Delegation:  
    def __init__(self, data):  
        self.stack = data 
    def __getattr__(self, name):  
        print 'Delegating %s ..' % name,   # 정의되어 있지 않은 속성을 참조하려 하므로.. 
        return getattr(self.stack, name)   # self.stack의 속성을 대신 이용한다. 
 
a = Delegation([1,2,3,1,5]) 
print a.pop() 
print a.count(1)
