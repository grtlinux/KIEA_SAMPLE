#!/bin/sh

# IP 주소를 취득하고 싶은 호스트명 정의
fqdn="www.google.com"

echo "Address if $fqdn"
echo "========="

# host 명령어로 IP 주소를 취득, awk 가공해서 출력
# (1)
host $fqdn | \
# (2)
awk '/has address/ {print $NF,"IPv4"} \
/has IPv6 address/ {print $NF,"IPv6"}'
