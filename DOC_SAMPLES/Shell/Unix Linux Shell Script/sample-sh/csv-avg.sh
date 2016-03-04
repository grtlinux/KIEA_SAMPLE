#!/bin/sh

# CSV 파일이 존재하지 않으면 종료
#(1)
if [ ! -f "$1" ]; then
  echo "대상 CSV 파일이 존재하지 않습니다: $1" >&2
  exit 1
fi

# 확장자를 제외한 파일명 취득
filename=${1%.*}   #(2)

#(3)
awk -F, '{sum += $3} END{print sum / NR}' "$1" > ${filename}.avg
