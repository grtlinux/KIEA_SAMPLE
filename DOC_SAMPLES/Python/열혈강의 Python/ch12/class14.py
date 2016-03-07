# class15.py
class Attr: 
        def __init__(self): 
                self.mem1 = 1 
        def method1(self): 
                print 'method1' 
        def __getattr__(self, name): 
                if name == 'mem3': return 3 
                raise AttributeError 
        def __setattr__(self, name, value): 
                print '__setattr__(%s)=%s called' % (name, value) 
                self.__dict__[name] = value 
        def __delattr__(self, name): 
                print '__delattr__(%s) called' % name 
                del self.__dict__[name] 
 
                 
a = Attr() 
print a.mem1 
a.method1() 
print a.mem3 
a.mem5 = 5 
print a.mem5 
del a.mem5 

