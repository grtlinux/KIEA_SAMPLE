#!/bin/sh

# 감시할 스왑 발생 횟수. 이 숫자를 넘기면 경고
swapcount_limit=10         #(1)

# vmstat 명령어 출력에서 스왑인, 스왑아웃 값 취득
#(2)
swapcount=$(vmstat 1 6 | awk 'NR >= 4 {sum += $7 + $8} END{print sum}')

# 스왑 횟수가 허용값을 넘기면 경고
#(3)
if [ "$swapcount" -ge "$swapcount_limit" ]; then
  # 현재 시각을 [2013/02/01 13:15:44] 형태로 조합
  #(4)
  date_str=$(date '+%Y/%m/%d %H:%M:%S')

  # 스왑 발생 경고 출력
  #(5)
  echo "[$date_str] Swap Alert: $swapcount (si+so)"
  /home/user1/bin/alert.sh
fi
