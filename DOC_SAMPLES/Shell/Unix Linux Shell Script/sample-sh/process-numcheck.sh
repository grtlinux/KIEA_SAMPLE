#!/bin/sh

# 감시할 프로세스 명령어와 프로세스 허용 수
#(1)
commname="/home/user1/bin/calc"
threshold=3

# 프로세스 개수 카운트
#(2)
count=$(ps ax -o command | grep "$commname" | grep -v "^grep" | wc -l)

# 프로세스 수가 허용값 이상이면 경고 처리
#(3)
if [ "$count" -ge "$threshold" ]; then
  echo "[ERROR] 프로세스 $commname 다중 실행($count)" >&2
  /home/user1/bin/alert.sh
fi
