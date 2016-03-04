#!/bin/sh

# 내려받을 파일 URL 경로, 파일명 지정
#(1)
url_path="http://www.example.org/"
filename="sample.dat"

# 파일 내려받기. 내려받기에 성공하면 md5 해시값 표시
# Mac/FreeBSD라면 md5sum 명령어가 아니라 md5 명령어 사용
#(2)
curl -sO "${url_path}${filename}" && md5sum "$filename"

# 내려받기 파일을 삭제하고 종료
#(3)
rm -f "$filename"
