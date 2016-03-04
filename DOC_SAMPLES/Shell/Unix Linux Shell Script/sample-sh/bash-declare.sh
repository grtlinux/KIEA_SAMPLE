#!/bin/bash

url_template="http://www.example.org/download/img_%03d.jpg"

# 카운터 변수 count를 정수형으로 선언
#(1)
declare -i count=0

while [ $count -le 10 ]
do
  url=$(printf "$url_template" $count)
  curl -O "$url"

  # count를 1 늘림. expr 명령어 없이 계산식만으로 가능
  #(2)
  count=count+1
done
