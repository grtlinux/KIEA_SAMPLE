#!/bin/sh

# 미리 설정하지 않으면 에러가 발생하는 환경 변수 정의
envname="TMPVAR"   #(1)

# env 명령어로 환경 변수 일람을 표시해서 cut 명령어로
# *첫 번째 값을 표시 [-f 1]
# *분리자는 :[-d "="]로 표시
env | cut -f 1 -d "=" > env.lst   #(2)

# 확인할 환경 변수명이 env.lst와 같은지로
# 정의되어 있는지 확인
grep -q "^${envname}$" env.lst   #(3)

#(4)
if [ $? -eq 0 ]; then
  # 환경 변수가 설정되어 있으면 start.sh 실행
  #(5)
  echo "환경 변수 $envname가 설정되어 있습니다."
  ./start.sh
else
  echo "환경 변수 $envname가 설정되어 있지 않습니다."
fi
