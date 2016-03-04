#!/bin/sh

umask 077  #(1)

# 권한 600인 임시 파일에 echo 명령어 결과 출력
echo "ID: abcd123456" > idinfo.tmp  #(2)
