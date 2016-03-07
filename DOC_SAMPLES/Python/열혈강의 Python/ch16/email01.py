# email01.py 
import smtplib 
from email.MIMEText import MIMEText 
 
HOST = 'smtp.server'            # SMTP 서버 
me = 'gslee@mail.gwu.ac.kr'     # 내 주소 
you = 'myfriend@somewhere.com'  # 받을 사람 주소 
contents = ''' 
메일 시험중.. 
파이썬으로 보내는 메일임.. 
''' 
msg = MIMEText(contents, _charset='euc-kr') 
msg['Subject'] = 'I love 파이썬'        # 제목 
msg['From'] = me 
msg['To'] = you 
 
s = smtplib.SMTP(HOST) 
s.sendmail(me, [you], msg.as_string()) 
s.quit() 
