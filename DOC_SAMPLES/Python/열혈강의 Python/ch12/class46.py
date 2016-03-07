# class46.py
class Delegation:  
    def __init__(self, data):  
        self.stack = data 
    def __getitem__(self, k): 
        return self.stack[k] 
    def __getattr__(self, name):  
        print 'Delegating %s ..' % name, 
        return getattr(self.stack, name)    
 
