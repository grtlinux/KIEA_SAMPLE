#!/bin/bash

# 다음과 같은 처리를 하는 경우를 가정
#   script.sh : 데이터 출력
#   sort-data.sh : 데이터 정렬
#   calc.sh : 출력 데이터 계산
#(1)
./script.sh | ./sort-data.sh | ./calc.sh > output.txt

# 다른 명령어를 실행하면 PIPESTATUS값이 사라지므로
# 결과를 복사해둠
#(2)
pipe_status=("${PIPESTATUS[@]}")

# 파이프 처리 중에 명령어 성공, 실패 확인
# sort-data.sh 종료 스테이터스가 0이 아닌지 확인
#(3)
if [ "${pipe_status[1]}" -ne 0 ]; then
  echo "[ERROR] sort-data.sh에 실패했습니다." >&2
fi
