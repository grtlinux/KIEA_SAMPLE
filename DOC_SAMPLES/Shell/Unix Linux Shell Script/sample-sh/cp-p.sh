#!/bin/sh

backup_dir="/home/user1/backup"

# myapp 디렉터리를 $backup_dir 밑에 백업 복사
while getopts "a" option
do
  case $option in
    a)
      cp -a myapp "$backup_dir"
      exit
      ;;
  esac
done

cp -R myapp "$backup_dir"
