#!/bin/sh

uptimelog="uptime.log"

: > $uptimelog  #(1)

for i in 1 2 3 4 5 6  #(2)
do
  uptime >> $uptimelog  #(3)
  sleep 10
done
