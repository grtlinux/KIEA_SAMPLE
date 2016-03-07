# generator01.py
from __future__ import generators
def fibonacci(a=1, b=1): 
    while 1: 
        yield a 
        a, b = b, a+b 

t = fibonacci()  # t는 발생자
for i in range(11): 
    print t.next(), 

for k in fibonacci():
    if k > 100: break
    print k,
