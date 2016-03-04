#!/bin/sh

# 대상 IP 주소를 명령행 인수로 지정하지 않으면 에러 표시 후 종료
if [ -z "$1" ]; then   #(1)
  echo "IP 주소를 지정하세요." >&2
  exit 1
fi

# 대상 네트워크라면 ping 명령어 실행
case "$1" in
  192.168.2.*|192.168.10.*)   #(2)
    ping -c 1 "$1" > /dev/null 2>&1  #(3)

    #(4)
    if [ $? -eq 0 ]; then
      echo "Ping to $1 : [OK]"
    else
      echo "Ping to $1 : [NG]"
    fi
  ;;
  *)  #(5)
    echo "$1 테스트 대상이 아닙니다." >&2
    exit 2
  ;;
esac
