#!/bin/sh

# 실행 횟수
count=0   #(1)
# 통신 대상 서버
server="192.168.2.105"   #(2)

# 시그널 USR1 트랩 설정. 현재 $count 표시
#(3)
trap 'echo "Try Count: $count"' USR1

# nc 명령어로 연속 통신 확인 반복
#(4)
while [ "$count" -le 1000 ]
do
  # 카운터 1 늘리고 nc 명령어 실행
  # 마지막에 1초 대기
  #(5)
  count=$(expr $count  + 1)
  nc -zv "$server" 80
  sleep 1
done
