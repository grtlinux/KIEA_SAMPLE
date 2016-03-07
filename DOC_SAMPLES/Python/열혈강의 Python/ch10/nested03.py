# nested03.py
def f(x):  
    def g(i):  
        print i,  
        if i: g(i-1)  
    g(x)  
   
f(3)  
