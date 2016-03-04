#!/bin/sh

# 테스트 통신할 서버 정의
#(1)
ipaddr="192.168.2.1"
port=80

# 1에서 10까지 정수값 난수를 펄 한 줄 명령어로 생성
#(2)
waittime=$(perl -e 'print 1 + int(rand(10))')

# 테스트 명령어를 대기 시간을 두고 2번 실행
#(3)
nc -w 5 -zv $ipaddr $port
echo "Wait: $waittime sec."
sleep $waittime
nc -w 5 -zv $ipaddr $port
