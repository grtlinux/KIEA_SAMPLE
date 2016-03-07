# zombie.py
import os
import time

pid = os.fork()   # 이 시점에서 프로세스가 두 개로 나뉜다.
if pid:
        while 1: time.sleep(100)  # 종료하지 않는다
else:
        os._exit(42)
