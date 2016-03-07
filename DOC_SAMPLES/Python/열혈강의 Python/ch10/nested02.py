# nested02.py
x = 2           <--- global 
def F():  
    x = 1  
    print filter(lambda a: a > x, range(-5, 5))  
   
F() 
