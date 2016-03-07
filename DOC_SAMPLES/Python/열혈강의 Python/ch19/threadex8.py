# threadex8.py
import threading

g_count = 0

class MyThread(threading.Thread):
    def run(self):
        global g_count
        for i in range(10):
            lock.acquire()      # 락을 얻고
            g_count += 1        # 배타적으로 실행권을 얻어서 처리하고
            lock.release()      # 락을 푼다

lock = threading.Lock()
threads = []
for i in range(10):
    th = MyThread()
    th.start()
    threads.append(th)

for th in threads:
    th.join()
print 'Exiting', g_count
