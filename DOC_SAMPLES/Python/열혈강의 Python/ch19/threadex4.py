# threadex4.py
import thread, time

NoOfThread = 5        # 생성할 쓰레드 수
threadexit = [0] * NoOfThread  # 쓰레드 수 만큼의 값을 갖는 리스트 생성

def counter(id, count):
    for i in range(count):
        print 'id %s --> %s' % (id, i)
    threadexit[id] = 1    # 쓰레드 종료를 표시 함

for i in range(NoOfThread):
    thread.start_new_thread(counter, (i, 5))

while 0 in threadexit:    # 전체 쓰레드가 종료하기를 기다림
    time.sleep(0.1)       # CPU 자원 소모를 막기 위해서 0.1초씩 대기 상태로 들어감
print 'Exiting', threadexit
