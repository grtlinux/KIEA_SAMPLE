# redirect.py 
 
import sys 
 
f = open('t.txt', 'w') 
stdout = sys.stdout 	# 표준 출력 파일 저장해두기 
sys.stdout = f 		# 파일 객체로 변경
print 'Sample output' 
print 'Good' 
print 'Good' 
f.close() 
sys.stdout = stdout	# 필요하면 복구
