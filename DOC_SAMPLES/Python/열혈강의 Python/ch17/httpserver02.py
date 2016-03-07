# httpserver02.py
import SimpleHTTPServer
import BaseHTTPServer

PORT = 8000

Handler = SimpleHTTPServer.SimpleHTTPRequestHandler
httpd = BaseHTTPServer.HTTPServer(('', PORT), Handler)

print 'listening on port', PORT
httpd.serve_forever()
