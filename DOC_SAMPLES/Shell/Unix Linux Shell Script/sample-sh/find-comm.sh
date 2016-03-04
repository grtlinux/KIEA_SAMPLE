#!/bin/sh
 
# dir1/과 dir2/ 파일 목록 차이를 조사하기
#(1)
( cd dir1; find . -maxdepth 1 -type f -print | sort ) > dir1-file.lst
( cd dir2; find . -maxdepth 1 -type f -print | sort ) > dir2-file.lst

#(2) 
comm dir1-file.lst dir2-file.lst
