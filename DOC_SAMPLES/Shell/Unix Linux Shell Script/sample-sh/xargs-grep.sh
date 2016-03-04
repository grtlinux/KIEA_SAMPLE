#!/bin/sh

logdir="/var/log/myapp"

# 확장자 .log 파일에서 "ERROR" 문자열 검색
find $logdir -name "*.log" -print | xargs grep "ERROR" /dev/null  #(1)
