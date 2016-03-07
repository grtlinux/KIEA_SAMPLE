# stringfile.py 
import StringIO 

s = ''' 
Python is a cool little language. 
It is well designed, compact, easy to learn and fun to program in. 
Python strongly encourages the programmer to program in an OO-way, 
but does not require it. 
In my opinion, it is one of the best languages to use when learning OO-programming. 
The implementation of OO in Python is clean and simple, while being incredibly powerful. 
The basic Python execution environment is also the most interactive of the five discussed here, 
which can be very useful (especially when debugging code). 
''' 
 
f = StringIO.StringIO(s)   # 문자열 객체에서 파일 객체 얻어내기 
print f.read().upper()     # 대문자로 변환
