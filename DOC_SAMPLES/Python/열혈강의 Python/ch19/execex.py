# execex.py
import os

print 'ls를 실행하기 직전. 내 pid=%s' % os.getpid()
os.execl("/bin/ls", "ls", "-l")
print '이 글은 절대로 출력되지 않을 것임'
