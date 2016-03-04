#!/bin/sh

# 명령행 인수 확인
#(1)
if [ -z "$1" ]; then
  echo "세 번째 옥텟까지의 IP 주소를 인수로 지정하세요." >&2
  exit 1
fi

# 대상 IP를 외부 파일 ping_target.lst에서
# %ADDR_HEAD% 부분을 치환해서 순서대로 얻기
#(2)
for ipaddr in $(sed "s/%ADDR_HEAD%/$1/" ping_target.lst)
do
  # ping 명령어 실행. 출력 결과는 필요 없으므로 /dev/null로 리다이렉트
  ping -c 1 $ipaddr > /dev/null 2>&1

  # 종료 스테이터스로 성공, 실패 표시
  if [ $? -eq 0 ]; then
    echo "[Success] ping -> $ipaddr"
  else
    echo "[Failed] ping -> $ipaddr"
  fi
done
