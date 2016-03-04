#!/bin/sh
 
data_dir="/home/user1/myapp/data"
 
# 디렉터리 $data_dir의 서브디렉터리 용량 표시
du -sk ${data_dir}/*/ | sort -rn  #(1)
