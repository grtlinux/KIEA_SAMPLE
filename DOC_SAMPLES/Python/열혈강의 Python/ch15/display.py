#!/usr/local/bin/python

TemplateFile = 'temp.html'
def Display(contentDict):
        template = open(TemplateFile).read() # 틀 파일을 읽어들인다
        print "Content-Type: text/html\n\n"   # 문서 형식을 먼저 보내고
        print template % contentDict  # HTML 문서를 보낸다.

if __name__ == '__main__':
        content = {}
        content['body1'] = 'first body part'
        content['body2'] = 'second body part'
        Display(content)
