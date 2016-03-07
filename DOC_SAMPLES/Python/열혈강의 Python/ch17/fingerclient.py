# File : fingerclient.py
from socket import *
import string
PORT = 79            # finger port
def finger(user, host, port=PORT):
    s = socket(AF_INET, SOCK_STREAM)
    try:
        s.connect((host, port))
    except:
        return 'connect : Connection refused'
    s.send(user+'\r\n')
    lines = []
    while 1:
        str = s.recv(1024)
        if not str: break  # 원격 연결이 종료되었음
        lines.append(str)
    s.close()
    return string.join(lines)
if __name__ == '__main__':
    print finger('gslee', 'daisy.kwangwoon.ac.kr')
