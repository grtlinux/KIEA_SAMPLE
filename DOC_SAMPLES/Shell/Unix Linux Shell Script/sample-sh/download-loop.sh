#!/bin/sh

# 확인 대상 URL
#(1)
url="http://192.168.22.1/webapl/check"

# 무한 반복 시작
#(2)
while true
do
  # 명령어에서 테스트 대상 URL 내려받기
  # 파일 자체는 필요 없으므로 /dev/null로
  #(3)
  curl -so /dev/null "$url"

  # curl 명령어 종료 스테이터스로 OK, NG 판정
  #(4)
  if [ $? -eq 0 ]; then
    echo "[check OK]"
  else
    echo "[check NG]"
  fi

  # 5초 대기
  #(5)
  sleep 5
done
