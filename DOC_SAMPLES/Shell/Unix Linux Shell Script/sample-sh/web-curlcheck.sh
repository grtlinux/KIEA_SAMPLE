#!/bin/sh

# 감시 대상 URL 지정
#(1)
url="http://www.example.org/webapps/check"

# 현재 시각을 [2013/02/01 13:15:44] 형식으로 조합
#(2)
date_str=$(date '+%Y/%m/%d %H:%M:%S')

# 감시 URL에 curl 명령어로 접속해서 종료 스테이터스를 변수 curlresult에 대입
#(3)
httpstatus=$(curl -s "$url" -o /dev/null -w "%{http_code}")
curlresult=$?

# curl 명령어에 실패하면 HTTP 접속 자체에 문제가 있다고 판단
#(4)
if [ "$curlresult" -ne 0 ]; then
  echo "[$date_str] HTTP 접속 이상：curl exit status[$curlresult]"
  /home/user1/bin/alert.sh
# 400번대, 500번대 HTTP 스테이터스 코드라면 에러로 보고 경고
#(5)
elif [ "$httpstatus" -ge 400 ]; then
  echo "[$date_str] HTTP 스테이터스 이상：HTTP status[$httpstatus]"
  /home/user1/bin/alert.sh
fi
