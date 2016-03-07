# udp02.py 
# UDP 에코 클라이언트 
from socket import * 
 
HOST = 'icsh.gwu.ac.kr'   # 적당한 호스트로 변경 
ServerPort = 5001         # 서버 UDP 포트 
ClientPort = 6001         # 클라이언트 UDP 포트 
 
csock = socket(AF_INET, SOCK_DGRAM) 
csock.bind(('', ClientPort)) 
csock.sendto('HI', (HOST, ServerPort)) 
msg, addr = csock.recvfrom(1024) 
print msg, addr 
csock.sendto('Hello', (HOST, ServerPort)) 
msg, addr = csock.recvfrom(1024) 
print msg, addr 
