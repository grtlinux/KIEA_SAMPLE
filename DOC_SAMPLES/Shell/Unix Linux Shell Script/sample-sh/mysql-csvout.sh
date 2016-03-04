#!/bin/sh

# 데이터베이스 접속 설정
#(1)
DBHOST="192.168.11.5"
DBUSER="user1"
DBPASS="PASSWORD"
DBNAME="hamilton"

# mysql 명령어 경로
#(2)
MYSQL="/usr/bin/mysql"

# CSV 파일 출력 경로와 리포트 작성용 SQL문 파일명 지정
#(3)
csv_outputdir="/home/user1/output"
sqlfile="/home/user1/bin/select.sql"

# SQL 파일 확인
#(4)
if [ ! -f "$sqlfile" ];then
  echo "SQL 파일이 존재하지 않습니다: $sqlfile" >&2
  exit 1
fi

# CSV 파일 출력용 디렉터리 확인
#(5)
if [ ! -d "$csv_outputdir" ];then
  echo "CSV 출력용 디렉터리가 존재하지 않습니다: $csv_outputdir" >&2
  exit 1
fi

# 오늘 날짜를 YYYYMMDD로 취득
#(6)
today=$(date '+%Y%m%d')

# CSV 출력. -N으로 컬럼명 생략
# tr 명령어로 탭을 쉼표로 변환
#(7)
$MYSQL -h "${DBHOST}" -u "${DBUSER}" -p"${DBPASS}" -D "${DBNAME}" -N \
  < "$sqlfile" | tr "\t" "," > "${csv_outputdir}/data-${today}.csv"
