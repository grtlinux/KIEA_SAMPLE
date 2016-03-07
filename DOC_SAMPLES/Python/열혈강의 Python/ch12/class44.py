# class44.py
# Composition 
 
class Set: 
    def __init__(self, d=None):  
        self.data = d 
    def union(self, A): 
        res = self.data[:] 
        for x in A: 
            if x not in res: 
                res.append(x) 
        return Set(res) 
    def __getitem__(self, k): 
        return self.data[k] 
    def __repr__(self): 
        return `self.data` 
 
A = Set([1,2,3]) 
B = Set([3,4,5]) 
print A.union(B) 
