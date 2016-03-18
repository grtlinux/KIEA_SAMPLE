# echoClient.py : 

from socket import *

HOST = 'localhost'
PORT = 5007

s = socket(AF_INET, SOCK_STREAM)
s.connect((HOST, PORT))
s.send('Hello, world!!!')
data = s.recv(1024)
s.close()

print 'Received ', `data`

