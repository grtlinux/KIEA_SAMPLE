#!/bin/sh

prog=$(basename "$0")  #(1)

# 인수가 하나가 아니면 도움말을 표시하고 종료
if [ $# -ne 1 ]; then  #(2)
  echo "Usage: $prog <string>" 1>&2  #(3)
  exit 1
fi

# 명령행 인수 $1 표시
echo "Start: $prog ..."
echo " Input Argument: $1"  #(4)
echo "Stop: $prog ..."
