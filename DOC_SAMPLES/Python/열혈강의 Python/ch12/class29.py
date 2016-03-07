# class29.py
import time  
from threading import *         # 쓰레드 클래스를 제공하는 모듈 : threading 
 
class MyThread(Thread): # 부클래스 MyThread 정의 
    def __init__(self):  
        Thread.__init__(self) # 베이스 클래스의 초기화 루틴을 불러야 한다. 
 
    def run(self):              # 실제적으로 실행을 위해서 정의해주어야 할 부분. 
        for el in range(10):    # 10번 반복 
            print self.getName(), el    # 쓰레드 이름과, 숫자 출력 
            time.sleep(0.01)            # 0.01초 대기 
 
thread1 = MyThread()    # 쓰레드 객체(인스턴스) thread1 생성 
thread2 = MyThread()    # 쓰레드 객체(인스턴스) thread2 생성 
thread1.start()                 # 쓰레드 실행 시작 -> run 메써드가 호출됨 
thread2.start()
