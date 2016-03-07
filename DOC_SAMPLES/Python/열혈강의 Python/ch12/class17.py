# class17.py 
class Super: 
    def __init__(self): 
        print 'Super init called' 
 
class Sub(Super): 
    def __init__(self): 
        print 'Sub init called' 
 
s = Sub() 
