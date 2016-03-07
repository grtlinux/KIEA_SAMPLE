# class34.py
def getAttribute(klass, attr=None): 
        if attr == None: 
                attr = [] 
        for k in klass.__bases__: 
                getAttribute(k, attr) 
        for a in dir(klass): 
                if a not in attr: 
                        attr.append(a) 
        return attr 
 
# Super1과 Sub은 앞 절에서 정의된 클래스이다. 
print 'Attributes of Super1=', getAttribute(Super1) 
print 'Attributes of Sub=', getAttribute(Sub)
