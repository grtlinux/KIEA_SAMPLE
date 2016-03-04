#!/bin/bash

# 비교할 두 디렉터리 정의
#(1)
dir1="/var/tmp/backup1"
dir2="/var/tmp/backup2"

# comm 명령어로 출력을 비교. 중간 파일을 만들지 않아도
# 프로세스 치환으로 처리 가능
#(2)
comm <(ls "$dir1") <(ls "$dir2")
