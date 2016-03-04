#!/bin/sh

logdir="/var/log/myapp"

# 4일 전부터 2일 전까지 갱신된 파일 일람을 표시
find $logdir -name "*.log" -mtime -4 -mtime +1 -print
