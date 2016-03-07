# threadex6.py
import threading, time

# 쓰레드로 실행될 함수를 정의한다
def myThread(id):
    for i in range(10):
        print 'id=%s --> %s' % (id, i)
        time.sleep(0)    # CPU를 양도한다

threads = []        # 쓰레드 객체를 모아두는 리스트이다
for i in range(2):  # 쓰레드 두 개만 생성
    th = threading.Thread(target=myThread, args=(i,))  # 쓰레드 함수를 target인수로, 쓰레드로 전달될 인수를 args인수에 전달
    th.start()      # 쓰레드 실행 시작
    threads.append(th)  # 쓰레드 객체를 리스트에 저장

for th in threads:
    th.join()       # 각 쓰레드가 끝날 때까지 대기
print 'Exiting'
