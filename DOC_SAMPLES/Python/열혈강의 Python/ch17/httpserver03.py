# httpserver03.py
import CGIHTTPServer
import BaseHTTPServer

PORT = 8000

Handler = CGIHTTPServer.CGIHTTPRequestHandler
httpd = BaseHTTPServer.HTTPServer(('', PORT), Handler)

print 'listening on port', PORT
httpd.serve_forever()
