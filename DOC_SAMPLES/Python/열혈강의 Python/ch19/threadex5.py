# threadex5.py
import thread, time

NoOfThread = 5                  # 생성할 쓰레드 수
ThreadsLeft = NoOfThread        # 남아 있는 쓰레드 수
lock = thread.allocate_lock()

# 쓰레드 종료시 호출되는 뒤처리 함수
# 남아 있는 쓰레드 수를 1 감소한다.
# 기타 필요한 처리를 추가할 수 있다.
def threadexit(id):
    global ThreadsLeft
    print 'thread %d is quitting' % id
    lock.acquire()
    ThreadsLeft -= 1            # 남아 있는 쓰레드 수 1 감소. 안전을 위해서 상호 배제 영역으로 처리한다.
    lock.release()

def counter(id, count):
    for i in range(count):
        print 'id %s --> %s' % (id, i)
    threadexit(id)              # 쓰레드를 종료하면서 뒤처리 함수를 호출한다

for i in range(NoOfThread):
    thread.start_new_thread(counter, (i, 5))

while ThreadsLeft:      # 쓰레드가 존재하는 한 대기
    time.sleep(0.1)
print 'Exiting'
