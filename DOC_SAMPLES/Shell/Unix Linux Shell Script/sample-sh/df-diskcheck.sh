#!/bin/sh

# 감시할 디스크 사용률의 허용값 %
used_limit=90         #(1)
# df 명령어 출력 결과 임시 파일명
tmpfile="df.tmp.$$"   #(2)

# df 명령어로 디스크 사용량 표시. 첫 줄은 헤더이므로 제거
#(3)
df -P | awk 'NR >= 2 {print $5,$6}' > "$tmpfile"

# df 명령어로 디스크 사용량 표시. 첫 줄은 헤더이므로 제거
#(4)
while read percent mountpoint
do
  # "31%"을 "31"로 %기호 삭제
  #(5)
  percent_val=${percent%\%}

  # 디스크 사용량이 허용값 이상이면 경고
  #(6)
  if [ "$percent_val" -ge "$used_limit" ]; then
    # 현재시각을 [2015/02/01 13:15:44] 형식으로 조합
    #(7)
    date_str=$(date '+%Y/%m/%d %H:%M:%S')

    echo "[$date_str] Disk Capacity Alert: $mountpoint ($percent used)"
    /home/user1/bin/alert.sh
  fi
done < "$tmpfile"

# 임시 파일 삭제
#(8)
rm -f "$tmpfile"
