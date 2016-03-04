#!/bin/sh

logdir="/home/user1/myapp"

cd "$logdir"

# /home/user1/myapp/log 디렉터리에 있는 로그 파일을
# 암호 걸린 zip으로 아카이브
zip -e -r log.zip log
