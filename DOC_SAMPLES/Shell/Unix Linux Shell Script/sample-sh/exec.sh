#!/bin/sh

# TMPDIR 환경 변수 설정
TMPDIR="/disk1/tmp"
export TMPDIR

# exec 명령어로 myappd 실행. 명령행 인수를 "$@"로 넘김
#(1)
exec ./myappd "$@"
