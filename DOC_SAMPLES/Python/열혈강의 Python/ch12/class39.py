# class39.py
class MyDict(dict): 
	def keys(self): 
		K = dict.keys(self) # 언바운드 메써드 호출 
		K.sort() 
		return K 


d = MyDict({'one':1, 'two':2, 'three':3}) 
print d.keys()
