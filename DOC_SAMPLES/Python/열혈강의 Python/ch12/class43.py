# class43.py 
# Inheritance 
 
class Set(list): 
    def union(self, A): 
        res = self[:] 
        for x in A: 
            if x not in res: 
                res.append(x) 
        return Set(res) 
 
A = Set([1,2,3]) 
B = Set([3,4,5]) 
print A.union(B) 
