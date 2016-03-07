# httpserver04.py
import CGIHTTPServer
import BaseHTTPServer

PORT = 8000

class Handler(CGIHTTPServer.CGIHTTPRequestHandler):
    cgi_directories = ['/cgi-bin', '/public_html/cgi-bin']
httpd = BaseHTTPServer.HTTPServer(('', PORT), Handler)

print 'listening on port', PORT
httpd.serve_forever()
