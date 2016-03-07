# class18.py 
class Super: 
    def __init__(self): 
        print 'Super init called' 
 
class Sub(Super): 
    def __init__(self): 
        Super.__init__(self)    # 명시적으로 수퍼클래스의 생성자를 호출한다. 
        print 'Sub init called' 
 
s = Sub()
