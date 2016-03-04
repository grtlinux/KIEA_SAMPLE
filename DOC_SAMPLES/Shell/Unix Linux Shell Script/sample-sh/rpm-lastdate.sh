#!/bin/sh

# 지정한 목록 파일 존재 확인
#(1)
if [ ! -f "$1" ]; then
  echo "대상 패키지 목록 파일이 존재하지 않습니다: $1" >&2
  exit 1
fi

# 인수로 지정한 파일($1)에서 패키지 목록을 얻기
#(2)
pkglist=$(cat "$1")

# 설치된 rpm 갱신일자 출력
#(3)
rpm -q $pkglist --queryformat '%{INSTALLTIME:date} : %{NAME}\n'
