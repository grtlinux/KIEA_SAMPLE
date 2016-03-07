# readmail04.py 
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
mbox.user(userid) # 
mbox.pass_(passwd)  # 
noMsg, tsize = mbox.stat() 
 
spamKeyWords = ('광고', '홍보') 
for k in range(noMsg, 0, -1): 
    res = mbox.top(k, 0)[1] 
    headerMsg = '\n'.join(res) 
    f = StringIO.StringIO(headerMsg) 
    msg = mimetools.Message(f) 
    subject = decodeHeader(msg['subject']) 
    if filter(lambda x: x>=0, map(subject.find, spamKeyWords)): 
        print 'Deleting..', k, subject 
        mbox.dele(k) 
 
mbox.quit() 
