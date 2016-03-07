# class22.py
class Person: 
    def __init__(self, name, phone=None): 
        self.name = name 
        self.phone = phone 
    def __repr__(self): 
        return "name=%s tel=%s" % (self.name, self.phone) 
 
class Job: 
    def __init__(self, position, salary): 
        self.position = position 
        self.salary = salary 
    def __repr__(self): 
        return "position=%s salary=%s" % (self.position, self.salary) 
 
class Employee(Person, Job): 
    def __init__(self, name, phone, position, salary): 
        Person.__init__(self, name, phone)   # 언바운드 클래스 메써드 호출 
        Job.__init__(self, position, salary) # 언바운드 클래스 메써드 호출 
    def raisesalary(self, rate): 
        self.salary = self.salary * rate 
    def __repr__(self): 
        return Person.__repr__(self) + ' ' + Job.__repr__(self)  # 언바운드 클래스 메써드 호출 
 
e = Employee('gslee', 5244, 'prof', 300) 
e.raisesalary(1.5) 
print e
