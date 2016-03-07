# threadex7.py
import threading, time

# Thread의 서브 클래스를 정의한다
class MyThread(threading.Thread):
    def run(self):       # run 메써드는 쓰레드가 실행할 코드를 갖는다
        for i in range(10):
            print 'id=%s --> %s' % (self.getName(), i) # getName()는 쓰레드의 이름을 알려준다
            time.sleep(0) # CPU 양도

threads = []
for i in range(2):
    th = MyThread()      # 쓰레드 객체를 얻고
    th.start()           # 시작시킨다
    threads.append(th)   # 종료를 검사하기 위해 리스트에 잠시 저장

for th in threads:
    th.join()    # 쓰레드가 끝날 때 까지 대기
print 'Exiting'
