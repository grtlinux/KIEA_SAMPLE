#!/bin/sh

filecheck()
{
  if [ ! -f "$1" ]; then  #(2)
    echo "ERROR: File $1 does not exist." >&2
    exit 1;
  fi
}

# 파일명과 ID 목록 파일명을 지정해서
# 파일 존재 확인
#(1)
csvfile="data.csv"
idlistfile="$1"
filecheck "$idlistfile"
filecheck "$csvfile"

#(3)
while IFS=, read id name score
do
  grep -xq "$id" "$idlistfile"   #(4)
  if [ $? -eq 0 ]; then   #(5)
    echo $name
  fi
done < "$csvfile"
