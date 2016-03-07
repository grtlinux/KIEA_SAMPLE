# threadex2.py
import thread, time

g_count = 0

def counter(id, count):
    global g_count
    for i in range(count):
        print 'id %s --> %s' % (id, i)
        g_count = g_count + 1

for i in range(5):
    thread.start_new_thread(counter, (i, 5))

time.sleep(3)
print 'total counter = ', g_count
print 'Exiting'
