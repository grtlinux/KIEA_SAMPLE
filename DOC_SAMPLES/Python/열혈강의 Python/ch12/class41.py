# class41.py
class Animal:  
    def cry(self):  
        print '...' 
  
class Dog(Animal):  
    def cry(self):  
        print '멍멍'  

class Duck(Animal):  
    def cry(self):  
        print '꽥꽥'  

class Fish(Animal):  
    pass  
  
for each in (Dog(), Duck(), Fish()): 
    each.cry()
