#!/bin/sh

ipaddr="192.168.2.52"
faillog="fail-port.log"

for port in 80 2222 8080   #(1)
do
  nc -w 5 -z $ipaddr $port  #(2)
  #(3)
  if [ $? -ne 0 ]; then
    echo "Failed at port: $port" >> "$faillog"
  fi
done
