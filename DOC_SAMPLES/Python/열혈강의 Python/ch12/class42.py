# class42.py
class IntegerAdder: 
        def __init__(self, value=0): 
                self.value = value 
        def __add__(self, value): 
                try: 
                        return IntegerAdder(self.value + int(value)) 
                except: 
                        return self 
        def __repr__(self): 
                return `self.value` 
 
         
a = IntegerAdder(5) 
print a + 4  # 정수 덧셈 
print a + 4.5    # 실수 덧셈 
print a + '1324' # 정수로 변환 가능한 문자열 덧셈 
print a + 'abc'  # 정수로 변환 가능하지 않은 문자열 덧셈
