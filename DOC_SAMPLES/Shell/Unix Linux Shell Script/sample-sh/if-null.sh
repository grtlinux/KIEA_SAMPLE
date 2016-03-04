#!/bin/sh

# 데이터 파일 정의
#(1)
datafile="/home/user1/myapp/sample.dat" 

# 데이터 파일 존재 확인
#(2)
if [ -f "$datafile" ]; then
  # 용도 변경으로 필요 없으므로 주석 처리
  #(3)
  # ./myapp "$datafile"

  # 빈 if문은 작성할 수 없으므로 :(널 명령어) 추가
  #(4)
  :
else
  echo "데이터 파일이 존재하지 않습니다: $1" >&2
  exit 1
fi
