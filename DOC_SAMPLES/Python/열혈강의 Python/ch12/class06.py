# class06.py
class MyString: 
    def __init__(self, str): 
        self.str = str 
    def __div__(self, sep): 
        return string.split(self.str, sep) 
    __rdiv__ = __div 
