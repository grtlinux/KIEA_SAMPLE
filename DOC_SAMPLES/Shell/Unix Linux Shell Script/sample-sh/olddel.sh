#!/bin/sh
 
# 비교 대상 파일
log1="log1.log"
log2="log2.log"
 
# 인수 파일이 존재하는지 확인해서 존재하지 않으면 종료
#(1)
filecheck()
{
  if [ ! -e "$1" ]; then  #(2)
    echo "ERROR: File $1 does not exist." >&2
    exit 1;
  fi
}

#(3)
filecheck "$log1"
filecheck "$log2"
 
# 두 파일을 비교해서 오래된 쪽을 삭제
if [ "$log1" -nt "$log2" ]; then  #(4)
  echo "[$log1]->newer, [$log2]->older"
  rm $log2
else
  echo "[$log2]->newer, [$log1]->older"
  rm $log1
fi
