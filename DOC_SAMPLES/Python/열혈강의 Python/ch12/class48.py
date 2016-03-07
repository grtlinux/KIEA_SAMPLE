# class48.py
class Wrapper: 
    def __init__(self, data): 
        self.data = data 
    def __repr__(self): 
        return `self.data` 
    def __str__(self): 
        return str(self.data) 
    def get(self): 
        return self.data 
    def __getattr__(self, attrname): 
        return getattr(self.data, attrname) 

wComplex = Wrapper(5+3j)   # 복소수 감싸기 
print wComplex.imag 
print wComplex.real 
wList = Wrapper([1,2,3,1,5]) # 리스트 감싸기 
wList.append(6) 
print wList 
[1, 2, 3, 1, 5, 6] 
l = wList.get() 
print l[1:3] 
[2, 3] 
wList.sort() 
print wList
