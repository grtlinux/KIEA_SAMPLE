# redirect2str.py 
 
import sys 
import StringIO 
 
stdout = sys.stdout     # 표준 출력 파일 저장해두기 
sys.stdout = f = StringIO.StringIO()         # 출력 파일 방향 전환 
 
print 'Sample output' 
print 'Good' 
print 'Good' 
 
sys.stdout = stdout     # 표준 출력 복구 
s = f.getvalue()        # 내부 문자열 가져오기 
 
print 'Done------' 
print s
