#!/bin/sh

# 괄호 안은 서브셸
#(1)
(
  echo "Archive: /var/tmp/archive.tar"
  cd /var/tmp
  tar cvf archive.tar *.txt
)

# 스크립트 실행은 현재 디렉터리에서 처리
echo "Start: command.sh"
./command.sh
