# urllib03.py
import urllib 
g_opener = urllib.URLopener() 
def openpage(url): 
    try: 
        return g_opener.open(url) 
    except IOError, msg: 
        print url, 'open error' # 에러 발생 
f = openpage('http://www.python.org/') 
if f:     # 성공하면 문서 읽음 
    s = f.read() 
    info = f.info()  # 읽은 문서에 관한 정보 
    print info, '*type* :', info['content-type'] 
 
f = openpage('http://www.python.org/aaaa') # 없는 문서
