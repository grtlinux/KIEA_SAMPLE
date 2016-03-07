# class08.py
class MyNum: 
        def __init__(self, n): 
                self.n = n 
        def __coerce__(self, y): 
                return self.n, y 
 
         
a = MyNum(10) 
print a + 20 
print a * 30 
print a / 2 
print a % 2 
print divmod(a, 3) 
