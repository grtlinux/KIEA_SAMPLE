#!/bin/sh

count=0
trap ' echo
       echo "Try count: $count"
       exit ' INT   #(1)

while :
do
  curl -o /dev/null $1   #(2)
  count=`expr $count + 1`
  sleep 1
done
