#!/bin/sh

# 전송 속도를 측정할 임시 파일 크기 지정. 단위는 킬로바이트(KB)
filesize=1024  #(1)
# 전송 속도를 측정할 임시 파일명
filename="tmp.dat"  #(2)

# 전송에 사용할 임시 파일을 작성
dd if=/dev/zero of="$filename" count=$filesize bs=1024  #(3)

# FTP 접속해서 파일을 PUT
#(4)
server="192.168.2.5"
user="user1"
password="xxxxxxxxx"

echo "FTP Server: $server"

#(5)
time ftp -n "$server" << __EOT__
user "$user" "$password"
binary
put "$filename"
__EOT__

# 임시 파일 삭제
rm -f "$filename"
