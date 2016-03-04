#!/bin/sh

# 감시할 프로세스 명령어
#(1)
commname="/usr/sbin/httpd"

# 감시 프로세스 실행 명령어
#(2)
start="service httpd start"

# 감시 대상 명령어 프로세스 수 카운트
#(3)
count=$(ps ax -o command | grep "$commname" | grep -v "^grep" | wc -l)

# grep 명령어 출력 결과가 0이면 프로세스가
# 존재하지 않거나 이상 상황이라고 보고 프로세스 재실행
#(4)
if [ "$count" -eq 0 ]; then
  # 로그에 시각 표시
  #(5)
  date_str=$(date '+%Y/%m/%d %H:%M:%S')
  echo "[$date_str] 프로세스 $commname 찾지 못했습니다." >&2
  echo "[$date_str] 프로세스 $commname 실행" >&2

  # 감시 프로세스 실행
  #(6)
  $start
fi
