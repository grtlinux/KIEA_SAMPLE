# class11.py
class Square:  
        def __init__(self, end):  
                self.end = end  
        def __len__(self):  
                return self.end  
        def __getitem__(self, k): 
                if k < 0 or self.end <= k: raise IndexError, k  
                return k * k 
        def __getslice__(self, i, j): 
                if i < 0: i = 0 
                if j > self.end: j = self.end 
                return map(self.__getitem__, range(i, j)) 

s = Square(10) 
print s[1:5]    # s.__getslice__(1, 5) 
print s[1:100]  # s.__getslice__(1, 100)
print s[:] 
