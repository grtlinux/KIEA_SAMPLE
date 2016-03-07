# ftpsample.py
from ftplib import FTP
print '---connecting..'
ftp = FTP('ftp.kreonet.re.kr')
print '---logging in..'
ftp.login()
#ftp.login('usrname', 'password')
print '---getting list'
ftp.retrlines('LIST')
#ftp.retrlines('LIST', open('list.txt', 'w').write)
print '---getting dir'
print ftp.dir()
print '---getting nlist'
print ftp.nlst()
print '---downloading welcome.msg'
ftp.retrbinary('RETR welcome.msg', open('welcome.msg', 'wb').write)
print '---changing dir'
ftp.cwd('usr')
print '---getting list'
ftp.retrlines('LIST')
print '---quit'
ftp.quit()
print '---finished'
