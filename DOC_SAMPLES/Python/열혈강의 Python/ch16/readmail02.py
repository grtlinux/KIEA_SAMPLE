# readmail02.py 
import poplib 
import email 
import mimetypes 
from email import Header 
 
def decodeHeader(headerMsg): 
    L = Header.decode_header(headerMsg) 
    return ''.join([t[0] for t in L]) 
 
host = ''       # 적당한 값을 써 넣을 것 
userid = ''     # 적당한 값을 써 넣을 것 
passwd = ''     # 적당한 값을 써 넣을 것 
 
mbox = poplib.POP3(host)        # 서버에 연결 
mbox.user(userid)               # 로그인 
mbox.pass_(passwd)              # 로그인 
 
noMsg, tsize = mbox.stat() 
print noMsg, 'messages' 
if noMsg > 0: 
    #(server_msg, body, octets) = mbox.retr(noMsg)  # 최근 메일을 읽는다. 
    (server_msg, body, octets) = mbox.retr(1)       # 첫 메일을 읽는다 
    message = '\n'.join(body) 
    msg = email.message_from_string(message)        # 문자열에서 Message 객체로.. 
    print decodeHeader(msg['from'])   # 보낸 사람 
    print decodeHeader(msg['to'])     # 받는 사람 
    print decodeHeader(msg['subject'])# 제목 
    print msg['date']       # 날짜 
    counter = 1             # 마임 파트의 수 
    for part in msg.walk(): # 각각의 파트에 대해 
        if part.get_main_type() == 'multipart': 
            continue 
        filename = part.get_filename()      # 파일 이름을 얻고 
        if not filename:    # 이름이 없으면 만든다. 
            ext = mimetypes.guess_extension(part.get_type()) 
            if not ext: 
                ext = '.bin' 
            filename = 'part-%03d%s' % (counter, ext) 
        counter += 1 
        fp = open(filename, 'wb') 
        fp.write(part.get_payload(decode=1))        # 디코드 해서 파일로 저장 
        fp.close() 
        print filename, 'saved..' 
mbox.quit () 
