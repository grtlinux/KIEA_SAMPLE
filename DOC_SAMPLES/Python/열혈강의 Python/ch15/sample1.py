#!/usr/local/bin/python
import cgi

TemplateFile = 'temp.html' # 틀 파일 이름
FormFile = 'form.html'  # 폼 파일 이름

def Display(contentDict):
        template = open(TemplateFile).read()
        print "Content-Type: text/html\n\n"
        print template % contentDict

def DisplayForm():
        print "Content-Type: text/html\n\n"
        print open(FormFile).read()

def ProcessForm(form):
        d = {}
        keys = form.keys() # 키 리스트를 얻는다.
        for key in keys: # 폼 필드 값들을 새로운 사전에 다시 담는다.
                d[key] = form[key].value
        for key in ('name', 'email', 'color', 'comment'):
                if key not in keys: # 만일 정의된 것이 아니면 None으로
                        d[key] = None
        content = {}
        content['body1'] = '''
                <p><a href="mailto:%(email)s">%(name)s</a> </p>
                <p>좋아하는 색깔 : %(color)s</p>
                <p>설명 : </p>
                <p>%(comment)s</p>''' % d
        content['body2'] = '<font size=-1><center>python CGI sample1</center></font>'
        Display(content)

if __name__ == '__main__':
        form = cgi.FieldStorage()
        try:
                key = form['key'].value
        except:
                key = None
        if key == 'process':
                ProcessForm(form)
        else:
                DisplayForm()
