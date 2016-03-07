# sgml01.py
import sgmllib

class ExtrTextParser(sgmllib.SGMLParser): # SGMLParser에서 상속받은 클래스 정의
    def __init__(self, verbose=0): # verbose는 수퍼클래스에서 필요로 한다
        sgmllib.SGMLParser.__init__(self, verbose) # 수퍼클래스 초기화 해야 함
        self.data = ''   # 내가 필요한 변수 초기화
    def handle_data(self, data): # 모든 태그 사이의 텍스트가 검출되면 호출된다.
        self.data += data
    def getresult(self): # 나중에 결과를 얻기 위한 메써드
        return self.data


s = '''<html><head><title>This is title</title></head>
<body>
<h2>This is a heading</h2>
<A href="http://www.python.or.kr:8080/python/">Python Information Plaza</A>
<a href="mailto:gslee@mail.gwu.ac.kr">Gang Seong Lee</a>
<p> paragraph1 </p>
<p> p tage without closing end tag
<p> paragraph2 </p>
</body>
</html>'''

parser = ExtrTextParser()  # 우선 파서 인스턴스 객체를 만든다
parser.feed(s)  # 텍스트를 넣어 준다.
parser.close()  # 모드 넣었으면 종료한다.
print parser.getresult()  # 결과를 얻는다.
