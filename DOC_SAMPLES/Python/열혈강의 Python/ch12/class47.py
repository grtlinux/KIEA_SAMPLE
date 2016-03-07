# class47.py
class Delegation:  
    def __init__(self, data):  
        self.stack = data 
    def get(self): 
        return self.stack 
    def __getattr__(self, name):  
        print 'Delegating %s ..' % name, 
        return getattr(self.stack, name)    
 
a = Delegation([1,2,3,4,5]) 
print a.get()[-1] 
