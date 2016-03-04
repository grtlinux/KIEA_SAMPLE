#!/bin/sh

# 검색할 문자열 정의
search_text="ERROR 19:"

# 현재 디렉터리에서 확장자가 .log인 파일을 순서대로 처리
#(1)
for filename in *.log
do
  # 일치하는 줄 수를 -c 옵션으로 취득
  count=$(grep -c "$search_text" "$filename")  #(2)
  # printf 명령어로 오른쪽 정렬 6칸으로 변형해서 출력
  #(3)
  printf "%6s (%s)\n" "$count" "$filename"
done
