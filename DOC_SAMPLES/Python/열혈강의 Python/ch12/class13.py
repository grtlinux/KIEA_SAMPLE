# class13.py
class MyDict: 
        def __init__(self, d=None): 
                if d == None: d = {} 
                self.d = d 
        def __getitem__(self, k): # key 
                return self.d[k] 
        def __setitem__(self, k, v): 
                self.d[k] = v 
        def __len__(self): 
                return len(self.d) 

m = MyDict()        # __init__ 호출 
print m['day'] = 'light'  # __setitem__ 호출 
print m['night'] = 'darkness'  # __setitem__ 호출 
print m 
print m['day']            # __getitem__ 호출 
print m['night']          # __getitem__ 호출 
print len(m)              # __len__ 호출
