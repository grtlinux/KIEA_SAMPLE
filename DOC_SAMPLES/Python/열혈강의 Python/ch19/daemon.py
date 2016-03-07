# daemon.py
import os
import time
import sys

class Dummy:
        def write(self, s): pass

if os.fork():
        os._exit(0)

os.setpgrp()
os.umask(0)
sys.stdin.close()
sys.stdout = Dummy()
sys.stderr = Dummy()

# 여기서부터 데몬 프로그램 작성 시작하면 된다.
# 이 데몬은 5초 후에 자동적으로 종료된다.
time.sleep(5)
