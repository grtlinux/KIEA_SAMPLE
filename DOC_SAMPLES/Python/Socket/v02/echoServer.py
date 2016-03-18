# echoServer.py : server python program for echo

from socket import *

HOST = ''
PORT = 5007

s = socket(AF_INET, SOCK_STREAM)
s.bin((HOST, PORT))
s.listen(1)

conn, addr = s.accept()
print 'Connected by ', addr

while 1:
	data = conn.recv(1024)
	if not data: break
	conn.send(data)

conn.close()
