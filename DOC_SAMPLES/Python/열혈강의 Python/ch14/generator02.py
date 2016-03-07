# generator02.py
class Odds:  
        def __init__(self, limit=None):  
                self.data = -1 	# 초기값
                self.limit = limit 	# 한계값
        def __iter__(self):  
                return self  	# 반복자 리턴
        def next(self):  	# 반복자 메써드
                self.data += 2 	# 매번 2를 더한다
                if self.limit and self.limit <= self.data: # 한계이면 예외 발생
                        raise StopIteration 
                return self.data 	# 계산된 홀수 리턴
 
for k in Odds(20): 
    print k, 
 
print list(Odds(20))
