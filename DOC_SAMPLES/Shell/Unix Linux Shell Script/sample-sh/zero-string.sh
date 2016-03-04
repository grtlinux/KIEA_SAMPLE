#!/bin/sh

# 추출 조건 등 정의
#(1)
match_id=1          # 추출할 ID
csvfile="data.csv"  # CSV 파일 지정

# CSV 파일이없으면종료
#(2)
if [ ! -f "$csvfile" ]; then
  echo "CSV 파일이 존재하지 않습니다: $csvfile" >&2
  exit 1
fi

#CSV 파일 읽기
#(3)
while read line
do
  # 각 컬럼을 cut으로 추출
  #(4)
  id=$(echo $line | cut -f 1 -d ',')
  name=$(echo $line | cut -f 2 -d ',')

  # ID 컬럼이 셸 변수 match_id로 지정한 ID와 일치하면
  # 이름 필드 표시
  #(5)
  if [ "$id" -eq "$match_id" ]; then
    echo "$name"
  fi
done < "$csvfile"
