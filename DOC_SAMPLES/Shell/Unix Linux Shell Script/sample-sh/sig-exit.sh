#!/bin/sh

# 임시 파일을 정의, 빈 파일로 초기화
#(1)
tmpfile="calctmp.$$"
: > "$tmpfile"

# 트랩 설정. 종료할 때 임시 파일 삭제
#(2)
trap 'rm -f "$tmpfile"' EXIT

# 오래 걸리는 계산을 하는 외부 스크립트 실행
#(3)
./calcA.sh >> "$tmpfile"
./calcB.sh >> "$tmpfile"

# 계산 결과를 더해서 최종 합계를 계산
#(4)
awk '{sum += $1} END{print sum}' "$tmpfile"
