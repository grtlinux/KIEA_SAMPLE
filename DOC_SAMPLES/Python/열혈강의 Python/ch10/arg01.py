# arg01.py 
def area(height, width): 
    return height * width 

# 순서가 아닌 이름으로 값이 전달된다. 
a = area(width=20, height=10) 
print a 
b = area(height='height strng ', width=3) 
print b
