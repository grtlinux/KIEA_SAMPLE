#!/bin/sh

# netstat 명령어 출력에서 디폴트 게이트웨이 얻기
# 첫 번째 컬럼이 default인 줄의 두 번째 컬럼을 추출
# (1)
gateway=$(netstat -nr | awk '$1 == "default" {print $2}')

# 디폴트 게이트웨이에 ping
# (2)
ping -c 1 $gateway > /dev/null 2>&1

# ping 종료 스테이터스로 성공, 실패 확인
# (3)
if [ $? -eq 0 ]; then
  echo "[Success] ping -> $gateway"
else
  echo "[Failed] ping -> $gateway"
fi
