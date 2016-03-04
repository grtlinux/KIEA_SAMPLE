#!/bin/sh
username="user1"
server="192.168.1.5"

#(1)
tar cvf - myapp/log | ssh ${username}@${server} "cat > /backup/myapplog.tar"
