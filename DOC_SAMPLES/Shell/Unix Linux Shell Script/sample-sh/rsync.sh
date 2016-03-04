#!/bin/sh
log_dir="/home/user1/myapp/log"
backup_dir="/backup/myapp"

# /home/user1/myapp/log 안에 있는 로그 파일을
# /backup/myapp/log 디렉터리에 복사
rsync -av "$log_dir" "$backup_dir"
