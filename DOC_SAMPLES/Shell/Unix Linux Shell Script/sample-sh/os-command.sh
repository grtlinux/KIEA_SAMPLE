#!/bin/sh

# echo 명령어 경로를 환경에 따라 바꿔서 셸 변수 ECHO에 대입
#(1)
case $(uname -s) in
  # Mac이면 셸 내장이 아니라 /bin/echo 사용
  #(2)
  Darwin)
    ECHO="/bin/echo"
    ;;
  *)
    ECHO="echo"
    ;;
esac

#(3)
$ECHO -n "이것은 줄이 이어진"
$ECHO "메시지입니다."
