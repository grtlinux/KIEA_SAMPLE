# xmlrpctest02.py
import SocketServer
import xmlrpcserver
import xmlrpclib
import __main__    # 현재 모듈의 네임 스페이스를 얻기위해

class System:
    # 지원하는 전체 메써드를 얻는 메써드
    def listMethods(self, method, nr):
        res = []
        for obj in [System, Sample]:  # 여기에 오픈하는 클래스를 추가하면 된다.
            res += _listMethods(obj)
        return res

# 샘플 클래스
class Sample:
    def mytest(self, method, nr): # 입력 받은 인수는 nr이고 method는 메써드 이름인데 무시한다.
        return nr

# 내부용 함수. 클래스 인스턴스 객체로부터 '객체.메써드' 형식의 이름 목록을 얻는다.
def _listMethods(classobj):
    return [classobj.__name__ + '.' + methodname for methodname in dir(classobj) if methodname[:2] != '__']

# 인스턴스 객체를 생성한다.
# 매번 RPC가 호출될 때 마다 이 스크립트는 처음부터 다시 수행된다.
# 즉, 매 호출시에 새로운 인스턴스 객체가 만들어진다.
system = System()
sample = Sample()

class TestRequestHandler(xmlrpcserver.RequestHandler):
    #Override method:
    def setup(self):
        self.sample = Sample()
        xmlrpcserver.RequestHandler.setup(self)

    def call(self, method, params):
        print "Dispatching: ", method, params
        try:
            # A.B 형식으로 되어있으면 이름 A, B를 분리한다.
            methods = method.split('.')

            # 이 모듈의 이름 공간을 얻는다.
            server_method = __main__

            #이름 공간 안에서 객체를 얻는다
            while len(methods) >= 1:
                server_method = getattr(server_method, methods[0])
                methods = methods[1:]
        except:
            raise AttributeError, "Server does not contain XML-RPC procedure %s" % method
        return server_method(method, params)


if __name__ == '__main__':
    server = SocketServer.TCPServer(('', 8000), TestRequestHandler)
    server.serve_forever()
