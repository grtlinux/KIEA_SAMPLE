# socketserver01.py 
import socket 
from SocketServer import ThreadingTCPServer, StreamRequestHandler 
 
PORT = 8001 
 
class RequestHandler(StreamRequestHandler): 
    def handle(self): 
        print 'connection from', self.client_address 
        conn = self.request 
        while 1: 
            msg = conn.recv(1024) 
            if not msg: 
                conn.close() 
                print self.client_address, 'disconnected' 
                break 
            print self.client_address, msg 
 
if __name__ == '__main__': 
    server = ThreadingTCPServer(('', PORT), RequestHandler) 
    print 'listening on port', PORT 
    server.serve_forever() 
