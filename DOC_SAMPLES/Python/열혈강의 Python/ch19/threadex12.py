# threadex12.py
import threading
import time
import random
from Queue import Queue

NR_CONSUMER = 10                  # 소비자 쓰레드의 수
NR_PRODUCER = NR_CONSUMER / 2     # 생산자 쓰레드의 수

que = Queue(10)

class Consumer(threading.Thread):
    def run(self):
        for i in range(5):
            print que.get()
            time.sleep(0.0)

class Producer(threading.Thread):
    def run(self):
        for i in range(10):
            que.put(random.randint(0, 20))
            time.sleep(0.0)

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
