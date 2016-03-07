'''
 sample chatting server
 chatserver.py

 programmed by Gang Seong Lee
 2000.7.20 --> 2002.1.10

 server:
   python chatserver.py

 client : telnet localhost 8037

 exit : /quit
'''

import socket
from SocketServer import ThreadingTCPServer, StreamRequestHandler
import thread

PORT = 8037
lock = thread.allocate_lock()   # 상호 배제 구현을 위한 락 객체

class UsersList:
    def __init__(self):
        self.users = {}

    def __contains__(self, name):   # in 연산자 메써드
        return name in self.users

    def addUser(self, conn, addr, name):
        if name in self.users:
            conn.send('이미 등록된 이름입니다.\n')
            return None
        lock.acquire()  # 상호 배제
        self.users[name] = (conn, addr)
        lock.release()
        # 각종 메시지 출력
        self.broadcastMessage('['+name+'] ' + '님께서 입장하셨습니다\n')
        conn.send('[%s]님 어서오세요.\n'%name)    # 본인에게 가는 메시지
        print len(self.users), 'connections'    # 서버에 표시되는 메세지
        return name

    def removeUser(self, name):
        if name not in self.users:
            return
        lock.acquire()  # 상호 배제
        del self.users[name]
        lock.release()
        self.broadcastMessage('['+name+'] '+'님께서 나가셨습니다\n')
        print len(self.users), 'connections'    # 서버에 표시되는 메세지

    def handleMessage(self, name, msg):
        if msg[0] != '/':
            self.broadcastMessage('['+name+'] ' + msg)
            return
        cmd = msg[1:].rstrip().split()
        if cmd[0] == 'quit':
            self.removeUser(name)
            return -1

    def broadcastMessage(self, msg):
        for conn, addr in self.users.values():
            conn.send(msg)

class RequestHandler(StreamRequestHandler):
    users = UsersList()
    # 접속 요구가 들어오면 호출된다.
    def handle(self):
        print 'connection from', self.client_address    # 서버에 표시되는 메세지
        conn = self.request
        try:
            name = self.readAndRegisterName()
            data = self.receiveline()
            while data:
                if self.users.handleMessage(name, data) == -1:
                    conn.close()
                    break
                data = self.receiveline()
        except socket.error:
            print 'Socket Error'
        print 'Disconnected from', self.client_address    # 서버 메세지
        self.users.removeUser(name)

    # 참여자 이름을 읽어서 등록한다.
    def readAndRegisterName(self):
        while 1:
            self.request.send('Name ? ')
            name = self.receiveline().strip()    # 이름 읽기
            if self.users.addUser(self.request, self.client_address, name):
                return name

    # 한 라인을 읽어들인다.
    def receiveline(self):
        line = []
        while 1:
            data = self.request.recv(1024)
            line.append(data)
            if data[-1] == '\n':
                return ''.join(line)


#-------------------------------------------------------
if __name__ == '__main__':
    server = ThreadingTCPServer(("", PORT), RequestHandler)
    print 'listening on port', PORT
    server.serve_forever()
