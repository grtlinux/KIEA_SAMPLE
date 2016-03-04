#!/bin/sh

# ping 실행 결과 스테이터스, 0이면 성공이므로 1로 초기화
#(1)
result=1

# 대상 서버가 명령행 인수로 지정되지 않으면 에러 종료
#(2)
if [ -z "$1" ]; then
  echo "대상 호스트를 지정하세요." >&2
  exit 1
fi

# ping 명령어를 3회 실행. 성공하면 result를 0으로
#(3)
i=0
while [ $i -lt 3 ]
do
  # ping 명령어 실행. 종료 스테이터스만 필요하므로
  # /dev/null에 리다이렉트
  #(4)
  ping -c 1 "$1" > /dev/null

  # ping 명령어 종료 스테이터스 판별. 성공하면 result=0으로 반복문 탈출
  # 실패하면 3초 대기 후 재실행
  #(5)
  if [ $? -eq 0 ]; then
    result=0
    break
  else
    sleep 3
    i=$(expr $i + 1)
  fi
done

# 현재 시각을 [2013/02/01 13:15:44] 형태로 조합
#(6)
date_str=$(date '+%Y/%m/%d %H:%M:%S')
# ping 실행 결과를 $result로 판별해서 표시
#(7)
if [ $result -eq 0 ]; then
  echo "[$date_str] Ping OK: $1"
else
  echo "[$date_str] Ping NG: $1"
fi
