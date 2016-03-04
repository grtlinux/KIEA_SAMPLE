#!/bin/sh

# FTP 접속설정
#(1)
server="192.168.2.5"
user="user1"
password="xxxxxxxxx"
dir="/home/user1/myapp/log"
filename="app.log"

#(2)
ftp -n "$server" << __EOT__
user "$user" "$password"
binary
cd "$dir"
get "$filename"
__EOT__
