# re02.py 
import re 
 
s = '''<a href="http://www.daum.net">daum</a>  
<a href='http://www.naver.com'>naver</a>  
<a href=http://www.chosun.com>chosun</a>  
<a href=http://www.chosun.com class>chosun</a>  
<a href="http://job.daum.net/ " target="new">  
<a href="http://go.daum.net/bin/go.cgi?relative=1&url=/Mail-bin/login_f.cgi%3Ferror%3Dlogin" class="tls0">  
'''  
p = re.compile('''href=([^'"]\S+?)[\s>]|href="([^"]*?)"|href='([^']*?)\'''', re.I) 
pos = 0 
while 1: 
        match = p.search(s, pos) 
        if match: 
                url = match.groups() 
                pos = match.end() 
                print filter(None, url)[0] 
        else: 
                break 
