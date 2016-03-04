#!/bin/sh

config="myapp.conf"

bak_filename="${config}.$(date '+%Y%m%d')"  #(1)

# 이미 myapp.conf.20131202 등이 있으면
# 초까지 넣어서 백업 파일 작성
#(2)
if [ -e $bak_filename ]; then
  bak_filename="${config}.$(date '+%Y%m%d%H%M.%S')"
fi

cp -v "$config" "$bak_filename"  #(3)
