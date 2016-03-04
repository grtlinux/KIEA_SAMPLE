#!/bin/sh

#(1)
while read ipaddr
do
  revlookup=$(host "$ipaddr")  #(2)

  #(3)
  if [ $? -eq 0 ]; then
    echo -n "$ipaddr,"   #(4)
    #(5)
    echo "$revlookup" | awk '{print $NF}' | sed 's/\.$//'
  else
    echo "$ipaddr,"
  fi

  #(6)
  sleep 1
done < $1
