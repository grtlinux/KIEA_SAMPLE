# class07.py
class MyString: 
    def __init__(self, str): 
        self.str = str 
    def __div__(self, sep):      
        return string.split(self.str, sep) 
    __rdiv__ = __div__ 
    def __neg__(self): 
        t = list(self.str) 
        t.reverse() 
        return ''.join(t) 
 
m = MyString("abcdef") 
print -m 
