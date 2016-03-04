#!/bin/sh

tomorrow=$(date "+%d" -d '1 day')  #(1)

#(2)
if [ "$tomorrow" = "01" ]; then
  echo "오늘은 말일입니다."
fi
