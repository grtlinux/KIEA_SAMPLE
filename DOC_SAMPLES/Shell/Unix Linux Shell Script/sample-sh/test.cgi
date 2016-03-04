#!/bin/sh

# CGI 헤더 출력
echo "Content-Type: text/plain"
echo

# 명령어를 실행해서 브라우저에 표시
echo "Test CGI: uptime"
uptime
