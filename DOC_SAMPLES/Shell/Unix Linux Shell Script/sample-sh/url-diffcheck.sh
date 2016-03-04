#!/bin/sh

# 감시대상 URL
#(1)
url="http://www.example.org/update.html"

# 내려받기 파일명 정의
#(2)
newfile="new.dat"
oldfile="old.dat"

# 파일 내려받기
#(3)
curl -so "$newfile" "$url"

# 이전에 내려받은 파일과 #(3)에서 내려받은 파일 비교
#(4)
cmp -s "$newfile" "$oldfile"

# cmp 명령어 종료 스테이터스가 0이 아니면 차이가 존재
#(5)
if [ $? -ne 0 ]; then
  # 현재 시각을 2013/02/01 13:15:44 형태로 조합
  #(6)
  date_str=$(date '+%Y/%m/%d %H:%M:%S')

  # 파일 변경 알림
  #(7)
  echo "[$date_str] 파일이 변경되었습니다."
  echo "대상 URL: $url"
  /home/user1/bin/alert.sh
fi

# (3)에서 내려받은 파일을 파일명을 변경해서 저장
#(8)
mv -f "$newfile" "$oldfile"
