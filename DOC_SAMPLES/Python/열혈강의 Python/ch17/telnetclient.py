# telnetclient.py
from telnetlib import Telnet
import getpass


def telnetClient(host):
    telnet = Telnet(host)
    telnet.read_until('login:')		# 로그인 문자열이 나올 때 까지 대기
    user = raw_input("Enter your remote account: ")	# 키보드 입력
    telnet.write(user+'\n')	# 사용자 이름 보낸다
    telnet.read_until('Password:')	# Password를 물을 때 까지 대기
    password = getpass.getpass()	# 패스워드를 받고
    telnet.write(password+'\n')		# 보낸다
    cmd = raw_input("Enter your command: ")	# 명령을 입력받는다.
    telnet.write(cmd+'\n')	# 명령을 보낸다.
    telnet.write('exit\n')	# 종료한다.
    return telnet.read_all()	# 모든 메시지를 출력한다.

if __name__ == '__main__':
    r = telnetClient('daisy')
    print r
