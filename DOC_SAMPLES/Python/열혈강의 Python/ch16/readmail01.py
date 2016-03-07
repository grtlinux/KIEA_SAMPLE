# readmail01.py 
 
import poplib 
 
host = 'mail.host.at.some.where'  # POP3를 지원하는 메일 서버를 지정한다. 
mbox = poplib.POP3(host) 
mbox.user('userid') # 사용자 id를 넘겨준다 
mbox.pass_('passwd')  # password를 넘겨준다 
 
noMsg, tsize = mbox.stat() 
print noMsg, 'messages' 
if noMsg > 0: 
    (server_msg, body, octets) = mbox.retr(noMsg) 
    message = '\n'.join(body) 
    print message 
 
mbox.quit () 
