# fileappend.py
f = open('removeme.txt', 'w')  # 파일 생성 모드로 오픈 
f.write('first line\n') 
f.write('second line\n') 
f.close() 
 
f = open('removeme.txt', 'a')  # 파일 추가 모드로 오픈 
f.write('third line\n') 
f.close() 
 
f = open('removeme.txt')  # 읽기 
print f.read()
