#!/bin/sh

# 변환 파일 출력용 디렉터리명
outdir="newdir"  #(1)

# 파일 출력용 디렉터리 확인
#(2) 
if [ ! -d "$outdir" ]; then
  echo "Not a directory: $outdir"
  exit 1
fi

#(3)
for filename in *.js
do
  # 빈 줄 또는 스페이스나 탭 문자만 있는 줄을 sed 명령어 d로 삭제
  #(4)
  sed '/^[[:blank:]]*$/d' "$filename" > "${outdir}/${filename}"
done
