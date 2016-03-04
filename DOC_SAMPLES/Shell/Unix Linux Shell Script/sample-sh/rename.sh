#!/bin/sh

for filename in *  #(1)
do
  case "$filename" in  #(2)
    *.htm | *.html)  #(3)
      # 파일명 앞 부분을 취득(index)
      headname=${filename%.*}  #(5)
     
      # 파일명을 .txt로 변환
      mv "$filename" "${headname}.txt"  #(6)
    ;;
  esac
done
