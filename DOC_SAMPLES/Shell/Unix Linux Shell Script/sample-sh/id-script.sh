#!/bin/sh

# 스크립트 실행을 허용할 유저 정의
#(1)
script_user="batch1"

# id 명령어로 현재 유저를 취득, 정의와 일치하는지 확인
#(2)
if [ $(id -nu) = "$script_user" ]; then
  # 허가 사용자면 배치 처리 실행
  #(3)
  ./batch_program
else
  #(4)
  echo "[ERROR] $script_user 유저로 실행하세요." >&2
  exit 1
fi
