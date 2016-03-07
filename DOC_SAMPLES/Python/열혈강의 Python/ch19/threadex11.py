# threadex11.py
import threading

eve = threading.Event()   # Event 객체 생성

class PrepareThread(threading.Thread):
    def run(self):
        # 뭔가를 준비하고, 준비됨을 알린다.
        eve.set()
        print 'Ready'

class ActionThread(threading.Thread):
    def run(self):
        # 앞에서 처리할 코드..
        print self.getName(), 'waiting..'
        # PrepareThread가 준비를 마칠 때까지 대기한다.
        eve.wait()
        # 본격적인 작업은 여기에..
        print self.getName(), 'done'

thlist = []
for i in range(5):
    thlist.append(ActionThread())

for th in thlist:
    th.start()

PrepareThread().start()

for th in thlist:
    th.join()

print 'Exiting'
