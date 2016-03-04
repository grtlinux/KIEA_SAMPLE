#!/bin/sh

# 설치할 패키지명 정의
#(1)
pkglist="httpd zsh xz git"

# 패키지 목록에서 순서대로 한 줄씩 읽기
#(2)
for pkg in $pkglist
do
  # yum 명령어로 패키지를 설치
  #(3)
  yum install $pkg -y
done
