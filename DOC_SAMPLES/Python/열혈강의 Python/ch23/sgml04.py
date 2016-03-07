# sgml04.py
import sgmllib, urllib

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

url = 'http://www.daum.net/'  # 문서를 읽을 URL
s = urllib.urlopen(url).read()
parser = ExtrLinkParser ()  # 우선 파서 인스턴스 객체를 만든다
parser.feed(s)  # 텍스트를 넣어 준다.
parser.close()  # 모드 넣었으면 종료한다.
print parser.getresult()  # 결과를 얻는다.
