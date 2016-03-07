# email02.py 
import smtplib 
import mimetypes 
import glob 
 
from email import Encoders 
from email.Message import Message 
from email.MIMEAudio import MIMEAudio 
from email.MIMEBase import MIMEBase 
from email.MIMEImage import MIMEImage 
from email.MIMEText import MIMEText 
 
HOST = 'smtp.server'            # SMTP 서버 
me = 'gslee@mail.kw.ac.kr'              # 내 주소 
receiver = ['friend1@some.com', 'friend2@some.com']# 받을 사람 주소 리스트 
 
outer = MIMEBase('multipart', 'mixed') 
outer['Subject'] = '첨부파일 메일 보내기' 
outer['From'] = me 
outer['To'] = ', '.join(receiver)   # 수신자 문자열 만들기 
outer.preamble = 'This is a multi-part message in MIME format.\n\n' 
outer.epilogue = '' # 이렇게 하면 멀티파트 경계 다음에 줄바꿈 코드가 삽입됨 
msg = MIMEText('디렉토리 파일들을 첨부합니다.', _charset='euc-kr') 
outer.attach(msg) 
 
files = glob.glob('*.*')          
for fileName in files: 
    ctype, encoding = mimetypes.guess_type(fileName) 
    if ctype is None or encoding is not None: 
        ctype = 'application/octet-stream' 
    maintype, subtype = ctype.split('/', 1) 
    if maintype == 'text': 
        fd = open(fileName) 
        msg = MIMEText(fd.read(), _subtype=subtype) 
    elif maintype == 'image': 
        fd = open(fileName, 'rb') 
        msg = MIMEImage(fd.read(), _subtype=subtype) 
    elif maintype == 'audio': 
        fd = open(fileName, 'rb') 
        msg = MIMEAudio(fd.read(), _subtype=subtype) 
    else: 
        fd = open(fileName, 'rb') 
        msg = MIMEBase(maintype, subtype) 
        msg.add_payload(fd.read()) 
        # Encode the payload using Base64 
        Encoders.encode_base64(msg) 
    fd.close() 
    msg.add_header('Content-Disposition', 'attachment', filename=fileName) 
    outer.attach(msg) 
 
# SMTP 서버를 통해서 메일 보내기 
s = smtplib.SMTP(HOST) 
s.sendmail(me, receiver, outer.as_string()) 
s.quit() 
