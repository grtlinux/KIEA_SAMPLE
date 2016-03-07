# echoclient.py 에코 - 클라이언트 프로그램
from socket import *
HOST = 'localhost'        # host name 현재는 한 컴퓨터 내에서 사용
PORT = 50007              # 서버와 같은 포트번호를 써야 함
s = socket(AF_INET, SOCK_STREAM)
s.connect((HOST, PORT))
s.send('Hello, world')
data = s.recv(1024)
s.close()
print 'Received', `data`
