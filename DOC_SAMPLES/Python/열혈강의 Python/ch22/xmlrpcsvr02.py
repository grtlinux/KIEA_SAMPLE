# xmlrpcsvr.py
from SimpleXMLRPCServer import *

class MyRequestHandler(SimpleXMLRPCRequestHandler):
    def _dispatch(self, method, params):
        try:
            server_method = getattr(self, "do_"+method)
        except:
            raise AttributeError, "No XML-RPC procedure do_%s" % method
        return server_method(*params)

    def log_message(self, format, *args):
        print format % args

    def do_incr(self, k):
        return k+1

if __name__ == '__main__':
    server = SimpleXMLRPCServer(('', 8000), MyRequestHandler)
    server.serve_forever()
