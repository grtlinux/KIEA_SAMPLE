# threadex9.py
import threading, time
import random

NR_CONSUMER = 10                  # 소비자 프로세스의 수
NR_PRODUCER = NR_CONSUMER / 2     # 생산자 프로세스의 수

buffer = []                       # 공유 버퍼
cv = threading.Condition()        # 조건 변수 생성

# 소비자
class Consumer(threading.Thread):
    def run(self):
        for x in range(5):         # 한 소비자는 5개의 정보를 소비한다
            cv.acquire()
            while len(buffer) < 1: # 정보가 없으면
                print 'waiting..'
                cv.wait()          # wait 한다
            print buffer.pop(0)    # 정보 소비
            cv.release()
            time.sleep(0.01)       # 잠시 대기

# 생산자
class Producer(threading.Thread):
    def run(self):
        for x in range(10):        # 한 생산자는 10개의 정보를 생산한다
            cv.acquire()
            buffer.append(random.randrange(0, 20)) # 0부터 19 사이의 난수를 발생하여 정보 생산
            cv.notify()            # cv에 대기하는 쓰레드가 있으면 깨워준다
            cv.release()
            time.sleep(0.02)       # 잠시 대기

con = []       # 소비자 쓰레드 리스트
pro = []       # 생산자 쓰레드 리스트

for i in range(NR_CONSUMER):
    con.append(Consumer())   # 소비자 쓰레드 생성

for i in range(NR_PRODUCER):
    pro.append(Producer())   # 생산자 쓰레드 생성

for th in con: # 소비자 쓰레드 먼저 시작
    th.start()

for th in pro: # 생산자 쓰레드 시작
    th.start()

for th in con: # 소비자가 종료할 때까지 대기
    th.join()

for th in pro: # 생산자가 종료할 때까지 대기
    th.join()

print 'Exiting'
