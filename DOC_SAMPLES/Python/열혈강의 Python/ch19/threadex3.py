# threadex3.py
import thread, time

g_count = 0
lock = thread.allocate_lock()
def counter(id, count):
    global g_count
    for i in range(count):
        print 'id %s --> %s' % (id, i)
        lock.acquire()
        g_count = g_count + 1
        lock.release()

for i in range(5):
    thread.start_new_thread(counter, (i, 5))

time.sleep(3)
print 'total counter = ', g_count
print 'Exiting'
