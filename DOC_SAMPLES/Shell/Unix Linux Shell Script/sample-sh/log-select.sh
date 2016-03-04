#!/bin/sh

logfile="access_log"

# 로그 파일이 존재하지 않으면 종료
#(1)
if [ ! -f "$logfile" ]; then
  echo "대상 로그 파일이 존재하지 않습니다: $logfile" >&2
  exit 1
fi

# HTTP 스테이터스를 외부 파일에 출력
#(2)
awk '$(NF-1)==404 {print $7}' "$logfile" > "${logfile}.404"
