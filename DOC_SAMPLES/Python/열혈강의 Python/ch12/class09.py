# class09.py
class Square: 
    def __init__(self, end): 
        self.end = end 
    def __len__(self): 
        return self.end 
    def __contains__(self, k): 
        return 0 <= k < self.end 
    def __getitem__(self, k): 
        if k < 0 or self.end <= k: raise IndexError, k 
        return k * k 

s1 = Square(10) 
print len(s1)  # s1.__len__() 
print 5 in s1  # s1.__contains__(5)  멤버쉽 테스트 
print s1[1]    # s1.__getitem__(1) 
print s1[4] 
print s1[20]    # 범위를 벋어난 참조
