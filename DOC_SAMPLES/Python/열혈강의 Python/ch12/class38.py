# class38.py
class MyList(list): 
	def __sub__(self, other):  # '-' 연산자 중복 함수 정의 
		L = self[:]         # 전체 리스트를 복사해서 얻는다 
		for x in other: 
			if x in L: L.remove(x)   # 각 항목을 하나씩 삭제한다 
		return L 

L = MyList([1,2,3,'spam', 4,5]) 
L = L - ['spam'] 
print L
