# echoserver.py 에코 - 서버 프로그램
from socket import *
HOST = ''                 # localhost를 의미하는 심볼릭 이름
PORT = 50007              # 임의의 사용가능 한 안쓰는 포트
s = socket(AF_INET, SOCK_STREAM)
s.bind((HOST, PORT))
s.listen(1)
conn, addr = s.accept() # 클라이언트에서 연결 요청이 올 때 까지 대기
print 'Connected by', addr
while 1:
    data = conn.recv(1024)
    if not data: break
    conn.send(data)
conn.close()
