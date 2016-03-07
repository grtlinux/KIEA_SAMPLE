# File : fingerserver.py
from socket import *
import string
users = {"gslee" : "Gang Seong Lee, Associate Professor in Kwangwoon University",
         "python" : "Wonderful and easy language everyone can learn"}
def main(HOST, PORT):
    s = socket(AF_INET, SOCK_STREAM)
    s.bind((HOST, PORT))
    s.listen(5)
    print 'Listening on port %s' % PORT
    while 1:
        conn, addr = s.accept() # 클라이언트에서 연결 요청이 올 때 까지 대기
        print 'Connected by ', addr   # for server
        # get user name
        try:
            name = ''
            while not '\n' in name:
                name = name + conn.recv(1024)
                if len(name) > 100:
                    raise 'BAD_REQUEST', 'user name too long'
            name = string.strip(name)
            userinfo = users[name]
        except:
            print 'Request failed :', name  # for server
            conn.send('No such user')
        else:
            print 'Request :', name
            conn.send(userinfo)
        conn.send('\r\n')
        conn.close()
if __name__ == '__main__':
    main('', 2000)  # we're using 2000 instead of 79 for testing
