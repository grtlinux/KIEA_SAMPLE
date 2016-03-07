# class20.py
class Person:  
    def __init__(self, name, phone=None):  
        self.name = name  
        self.phone = phone 
    def __repr__(self): 
        return '<Person %s %s>' % (self.name, self.phone) 
 
class Employee(Person):   
    def __init__(self, name, phone, position, salary):  
        Person.__init__(self, name, phone)       
        self.position = position  
        self.salary = salary  
    def __repr__(self): 
        s = Person.__repr__(self)    # 여기서 Person의 __repr__ 를 호출한다. 
        return s + ' <Employee %s %s>' % (self.position, self.salary) 
 
p1 = Person('gslee', 5284) 
m1 = Employee('kslee', 5224, 'President', 500) 
print p1 
print m1
