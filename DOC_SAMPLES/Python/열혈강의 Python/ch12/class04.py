# class04.py
from time import time, ctime, sleep 
 
class Life: 
    def __init__(self):         # 생성자 
        self.birth = ctime(time())      # 현재 시간에 대한 문자열을 얻는다. 
        print 'Birthday', self.birth    # 현재 시간 출력 
    def __del__(self):          # 소멸자 
        print 'Deathday', ctime(time()) # 소멸 시간 출력 
         
def test(): 
    mylife = Life() 
    print 'Sleeping for 3 sec' 
    sleep(3)    # 3초간 sleep(block) 상태로 감. 
 
test()
