#!/bin/sh

# (1)
ipaddr="192.168.2.1"
count=10

# (2)
echo "Ping to: $ipaddr"
echo "Ping count: $count"
echo "Ping average[ms]:"

# ping 명령어 실행 결과를 임시 파일에 출력
# (3)
ping -c $count $ipaddr > ping.$$

# "time=4.32 ms" 부분을 sed로 추출, awk로 평균값 계산
# (4), (5)
sed -n "s/^.*time=\(.*\) ms/\1/p" ping.$$ |\
awk '{sum+=$1} END{print sum/NR}'

#임시 파일 삭제
rm -f ping.$$
