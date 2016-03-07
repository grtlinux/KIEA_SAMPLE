# module01.py
a = 1 
b = 2 
 
def f(): 
    localx = 10 
    localy =  20 
    print '전역변수:', globals() 
    print 
    print '지역변수:', locals() 
 
     
f() 
print 
print '모듈 수준에서의 전역변수:', globals() 
print  
print '모듈 수준에서의 지역변수:', locals()
