#!/bin/sh

# 환경 초기화 셸 함수. 로그 출력할 곳을 설정한 setting.conf 읽음
#(1)
loadconf() {
  . ./setting.conf
}

# HUP 시그널로 설정을 다시 읽도록 정의
#(2)
trap 'loadconf' HUP

# 첫 초기화
#(3)
loadconf

# 무한 반복
#(4)
while :
do
  # uptime 명령어 결과를 setting.conf로 지정한 경로에 로그 출력
  #(5)
  uptime >> "${UPTIME_FILENAME}"
  sleep 1
done
