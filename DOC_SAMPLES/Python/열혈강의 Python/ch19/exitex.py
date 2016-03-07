# exitex.py
import os

print "원래 프로세스 PID=%s" % os.getpid()
pid = os.fork()   # 이 시점에서 프로세스가 두 개로 나뉜다.
if pid:
    print "난 부모 프로세스 PID=%s 내자식 PID=%s " % (os.getpid(), pid)
    pid, status = os.wait()  # 자식이 종료하기를 대기함
    print "자식 %s 는 리턴값 %s을 넘기고 종료함" % (pid, status >> 8)
else:
    print "난 자식 프로세스 PID=%s 내부모 PID=%s" % (os.getpid(), os.getppid())
    print "끝납니다 PID=%s" % os.getpid()
    os._exit(42)  # 자식 프로세스의 종료 코드를 넘겨주고 종료한다
