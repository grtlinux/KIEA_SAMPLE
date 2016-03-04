#!/bin/sh

ipaddr="192.168.2.1"

# (1)
macaddr=$(arp -ap | awk "/\($ipaddr\)/ {print \$4}")

# (2)
if [ -n "$macaddr" ]; then
  # (3)
  echo "$ipaddr -> $macaddr"
else
  # (4)
  echo "$ipaddr가 ARP 캐쉬에 없습니다."
fi
