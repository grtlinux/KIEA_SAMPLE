# pickleSample.py 
import pickle 

phone = {'tom': 4358382, 'jack': 9465215, 'jim': 6851325, 'Joseph': 6584321} 
list = ['string', 1234, 0.2345] 
tuple = (phone, list)  # 리스트, 터플, 사전의 복합 객체 
 
f = open('t2.txt', 'w')  # 파일 객체를 얻는다. 
# 파일로 출력 (pickling) 
pickle.dump(tuple, f) # 복합 객체 출력 
f.close() 
 
f = open('t2.txt', 'r') 
# 파일에서 읽어오기 (unpickling) 
x,y = pickle.load(f) # 터플의 내용을 x, y에 받는다. 
print x # x는 사전, y는 리스트 
print y
