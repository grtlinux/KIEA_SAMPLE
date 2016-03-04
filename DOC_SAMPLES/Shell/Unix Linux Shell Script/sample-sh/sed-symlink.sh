#!/bin/sh

filename="target.txt"

#(1)
if [ ! -e "$filename" ]; then
  # 대상 파일이 존재하지 않으면 에러 종료
  echo "ERROR: File not exists." >&2
  exit 1
#(2)
elif [ -h "$filename" ]; then
  # 대상 파일이 심볼릭 링크면 readlink 명령어로
  # 실제 파일을 대상으로 처리하기
  sed -i.bak "s/Hello/Hi/g" "$(readlink "$filename")"  #(3)
else
  sed -i.bak "s/Hello/Hi/g" "$filename"
fi
