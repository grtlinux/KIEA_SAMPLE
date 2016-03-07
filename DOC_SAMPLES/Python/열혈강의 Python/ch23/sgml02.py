# sgml02.py
import sgmllib

class TitleParser(sgmllib.SGMLParser):
    def __init__(self, verbose=0):
        sgmllib.SGMLParser.__init__(self, verbose)
        self.data = None
    def start_title(self, attrs): # <title> 태그가 읽혀지면 호출된다.
        self.data = ''  # 공 문자열로 초기화 한다.
    def end_title(self): # </title> 태그가 읽혀지면 호출된다.
        pass		# 여기서는 별로 할일이 없다.
    def handle_data(self, data):
        if self.data is not None: # 아직 self.data가 None이면 처리하지 않는다.
            self.data += data
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

parser = TitleParser ()  # 우선 파서 인스턴스 객체를 만든다
parser.feed(s)  # 텍스트를 넣어 준다.
parser.close()  # 모드 넣었으면 종료한다.
print parser.getresult()  # 결과를 얻는다.
