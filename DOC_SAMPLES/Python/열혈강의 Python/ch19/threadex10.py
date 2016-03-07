# threadex10.py
import threading

sem = threading.Semaphore(3)  # 세마포 객체 생성

class RestrictedArea(threading.Thread):
    def run(self):
        for x in range(500):
            # before stuff
            sem.acquire()
            # do something...    이 안에는 단지 3개의 쓰레드만이 존재할 수 있다.
            sem.release()
            # after stuff
thlist = []

# 100개의 쓰레드 생성
for i in range(100):
    thlist.append(RestrictedArea())

for th in thlist:
    th.start()      # 쓰레드 시작

for th in thlist:
    th.join()       # 종료 대기

print 'Exiting'
