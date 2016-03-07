# swap.py 
def swap(x, y): 
    return y, x         # 터플로 리턴된다. 
 
a, b = swap(b, a)       # 결과적으로 a, b = b, a와 동일 
x = swap(a, b) 
print x[0], x[1]           # 하나의 이름으로 값을 받아서 처리할 수도 있다.
