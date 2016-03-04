#!/bin/sh

# 변환한 파일을 출력할 디렉터리
outdir="newdir"    #(1)

# 파일 출력용 디렉터리 확인
#(2) 
if [ ! -d "$outdir" ]; then
  echo "Not a directory: $outdir"
  exit 1
fi

# 현재 디렉터리의 .html 파일이 대상
# (3)
for filename in *.html
do
  # grep 명령어로 meta 태그 Content-Type을 선택해서
  # sed 명령어로 charset=지정 부분 추출
  #(4)
  charset=$(grep -i '<meta ' "$filename" |\ 
  grep -i 'http-equiv="Content-Type"' |\ 
  sed -n 's/.*charset=\([-_a-zA-Z0-9]*\)".*/\1/p')

  # charset을 얻지 못하면 iconv 명령어를 실행하지 않고 건너뛰기
  #(5)
  if [ -z "$charset" ]; then
    echo "charset not found: $filename" >&2
    continue
  fi

  # meta 태그에서 추출한 문자 코드에서 UTF-8으로 변환
  # 디렉터리 $outdir에 출력  #(6)
  iconv -c -f "$charset" -t UTF-8 "$filename"  > "${outdir}/${filename}"
done
