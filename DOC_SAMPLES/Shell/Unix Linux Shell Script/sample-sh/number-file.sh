#!/bin/sh

#(1)
url_template="http://www.example.org/download/img_%03d.jpg"

# seq 명령어로 연속 번호를 생성
#(2)
for i in $(seq 10)
do
  url=$(printf "$url_template" $i)  #(3)
  curl -O "$url"  #(4)
done
