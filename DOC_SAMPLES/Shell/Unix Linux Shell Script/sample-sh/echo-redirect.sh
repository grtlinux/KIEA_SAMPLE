#!/bin/sh

# 중괄호로 그룹핑해서 리다이렉트를 하나로 합치기
#(1)
{
  echo "[Script start]"
  date
  ls
  echo "[Script end]"
} > output.log   #(2)
