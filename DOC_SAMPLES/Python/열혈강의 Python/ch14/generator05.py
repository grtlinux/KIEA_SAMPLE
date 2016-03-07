# generator05.py
def traverse(t): 
    if not isinstance(t, list):: 
        return [t] 
    res = [] 
    for el in t: 
        res.extend(traverse(el)) 
    return res 
 
a = [[1,2,3],4,5,[6,7],[8,9,10]] 
b = traverse(a) 
print b
