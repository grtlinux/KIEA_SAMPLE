#!/bin/bash

# 지정한 ID 파일에서 $id $status를 한 줄씩
# read 명령어로 읽어들임
#(1)
while read id status
do
  # 셸변수 id 첫 두 글자가 AC인지 확인
  #(2)
  if [ "${id:0:2}" = "AC" ]; then
    echo "$id $status"
  fi
done < "$1"
