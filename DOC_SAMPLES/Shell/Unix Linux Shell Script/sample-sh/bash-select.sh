#!/bin/bash

# 메뉴 프롬프트 정의
#(1)
PS3='Menu: '

# 메뉴 표시 정의. 메뉴 각 항목은 in에 목록으로 지정
# $item은 선택한 목록 문자열이, $REPLY에는 입력한 숫자가 대입됨
#(2)
select item in "list file" "current directory" "exit"
do
  case "$REPLY" in
    1)
      ls
      ;;
    2)
      pwd
      ;;
    3)
      exit
      ;;
    *)
      echo "Error: Unknown Command"
      ;;
  esac

  echo
done
