#!/bin/sh

logdir="/var/log/myapp"

#이번달 15일의 날짜 취득
thismonth=$(date '+%Y/%m/15 00:00:00') # (1)

# 지난달 날짜를 YYYMM으로 취득
# 1 month ago는 지난달의 같은 '날(일)'이 되므로 월말을 피하도록
# 변수 thismonth에 15일을 지정함
last_YYYYMM=$(date -d "$thismonth -1 month ago" '+%Y%m') # (2)

# 지난달 로그 파일을 아카이브
tar cvf ${last_YYYYMM}.tar ${logdir}/access.log-${last_YYYYMM}*
