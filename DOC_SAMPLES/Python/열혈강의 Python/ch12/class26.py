# class26.py
from UserList import UserList 
 
class myList(UserList): # UserList를 베이스 클래스로 하는 myList 정의 
    def __init__(self, d=None): 
        UserList.__init__(self, d)      # 베이스 클래스의 생성자 호출. 데이터 초기화 
    def push(self, d):          # 내가 정의하는 메써드 push 
        self.data.append(d)             # append로 대신한다. 실제 데이터는 self.data에 저장됨 
         
# 사용예 
ls = myList([1,2,3])    # 인스턴스 생성 
ls.push(4)              # push 
ls.push(5) 
print ls.pop()          # 5 
print ls.pop()          # 4 
print ls                        # [1, 2, 3]
