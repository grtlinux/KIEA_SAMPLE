#!/bin/sh

logdir="/var/log/myapp"

# 최종 갱신일이 1년 전 이상인 오래된 파일을 삭제
find $logdir -name "*.log" -mtime +364 -print | xargs rm -fv  #(1)
