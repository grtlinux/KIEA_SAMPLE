#!/bin/sh

# 감시할 CPU %idle 허용값. 이 값 이하면 경고
#(1)
idle_limit=10.0

# CPU %idle을 mpstat 명령어로 취득, 마지막 줄의 평균값을 추출
#(2)
cpu_idle=$(mpstat 1 5 | tail -n 1 | awk '{print $NF}')

# 현재 %idle과 허용값을 bc 명령어로 비교
#(3)
is_alert=$(echo "$cpu_idle < $idle_limit" | bc)

# 경고할 것인지 판별
# bc 명령어의 참거짓은 1이 참, 0이 거짓
#(4)
if [ "$is_alert" -eq 1 ]; then
  # 현재 시각을 [2013/02/01 13:15:44] 형태로 조합
  #(5)
  date_str=$(date '+%Y/%m/%d %H:%M:%S')

  # CPU %idle 저하를 경고로 출력
  #(6)
  echo "[$date_str] CPU %idle Alert: $cpu_idle (%)"
  /home/user1/bin/alert.sh
fi
