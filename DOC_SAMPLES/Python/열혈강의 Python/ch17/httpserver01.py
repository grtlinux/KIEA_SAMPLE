# httpserver01.py
import BaseHTTPServer
import sys
import time

PORT = 8000

class MyHandler(BaseHTTPServer.BaseHTTPRequestHandler):
    def do_GET(self):
        if self.path != '/':
            self.send_error(404, 'File not found')
            return
        self.send_response(200)
        self.send_header('Content-type', 'text/html')
        self.end_headers()
        stdout = sys.stdout
        sys.stdout = self.wfile
        self.printPage()
        sys.stdout = stdout
    def printPage(self):
        t = time.asctime()
        print '<html><body>접속한 시각: <b>%s<b></body></html>' % t

httpd = BaseHTTPServer.HTTPServer(('', PORT), MyHandler)
print 'listening on port', PORT
httpd.serve_forever()
