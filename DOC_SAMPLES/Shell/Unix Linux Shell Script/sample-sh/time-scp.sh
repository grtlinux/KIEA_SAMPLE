#!/bin/sh

# 테스트 전송 파일명, 전송할 곳 등 정의
#(1)
username="user1"         # ssh 사용자명
filename="transfer.dat"  # 전송 파일명
hostname="192.168.2.10"  # 전송 호스트
path="/var/tmp"          # 전송 경로
tmpfile="timetmp.$$"     # 시간 측정을 위한 임시 파일

# scp 명령어로 파일 전송
# time 명령어로 시간을 측정, 임시 파일에 출력
#(2)
(time -p scp -C "$filename" ${username}@${hostname}:"${path}" ) 2> "$tmpfile"

# time 명령어 출력 임시 파일에서 각 time 추출
#(3)
realtime=$(awk '/^real / {print $2}' "$tmpfile")
usertime=$(awk '/^user / {print $2}' "$tmpfile")
systime=$(awk '/^sys / {print $2}' "$tmpfile")

# CPU 사용 시간에서 CPU 사용률을 계산
#(4)
cpu_percentage=$(echo "scale=2; 100 * ($usertime + $systime) / $realtime" | bc )
echo "scp 전송 CPU 사용률: $cpu_percentage (%)"

# 임시 파일 삭제
rm -f "$tmpfile"
