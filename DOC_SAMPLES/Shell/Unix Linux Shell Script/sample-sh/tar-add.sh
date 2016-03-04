#!/bin/sh

# 월일로 아카이브 파일 지정(예: 201312.tar)
archivefile="$(date +'%Y%m').tar"  #(1)
# 오늘 날짜에서 로그 파일을 지정(예: 20131205.log)
logfile="$(date +'%Y%m%d').log"

# 월별 아카이브에 오늘 로그를 추가
tar rvf $archivefile log/$logfile  #(2)
