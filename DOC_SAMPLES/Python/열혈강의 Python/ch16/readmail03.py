# readmail03.py 
import poplib 
import email 
import mimetypes 
import StringIO 
import mimetools 
from email import Header 
 
def decodeHeader(headerMsg): 
    L = Header.decode_header(headerMsg) 
    return ''.join([t[0] for t in L]) 
 
host = ''       # 적당한 값을 써 넣을 것 
userid = ''     # 적당한 값을 써 넣을 것 
passwd = ''     # 적당한 값을 써 넣을 것 
 
mbox = poplib.POP3(host)   
mbox.user(userid) 
mbox.pass_(passwd) 
noMsg, tsize = mbox.stat()   
 
for k in range(1, noMsg+1): 
    res = mbox.top(k, 0)[1] 
    headerMsg = '\n'.join(res) 
    f = StringIO.StringIO(headerMsg)  
    msg = mimetools.Message(f) 
    print k, '보낸이: %s, 받는이: %s, 제목: %s, 날짜: %s' % (decodeHeader(msg['from']), decodeHeader(msg['to']), decodeHeader(msg['subject']), msg['date']) 
mbox.quit() 
