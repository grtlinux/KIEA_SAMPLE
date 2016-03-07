# sgml03.py
import sgmllib

class ExtrLinkParser(sgmllib.SGMLParser):
    def __init__(self, verbose=0):
        sgmllib.SGMLParser.__init__(self, verbose)
        self.data = []
    def start_a(self, attrs):
        for attr in attrs:
            if attr[0] == 'href': # 속성 이름 검사
                self.data.append(attr[1])
    def getresult(self):
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

parser = ExtrLinkParser ()  # 우선 파서 인스턴스 객체를 만든다
parser.feed(s)  # 텍스트를 넣어 준다.
parser.close()  # 모드 넣었으면 종료한다.
print parser.getresult()  # 결과를 얻는다.
