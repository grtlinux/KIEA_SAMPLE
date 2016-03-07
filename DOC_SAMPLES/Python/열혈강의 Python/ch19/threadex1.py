# threadex1.py
import thread, time

# 쓰레드로 수행될 함수
# 이 함수에서 벗어나면 쓰레드도 종료된다
def counter(id):
    for i in range(5):
        print 'id %s --> %s' % (id, i)
        time.sleep(0.1)

for i in range(5):
    thread.start_new_thread(counter, (i,)) # 5개의 쓰레드를 독립적으로 각각 실행시킨다

time.sleep(2)    # 잠시 대기
print 'Exiting'
