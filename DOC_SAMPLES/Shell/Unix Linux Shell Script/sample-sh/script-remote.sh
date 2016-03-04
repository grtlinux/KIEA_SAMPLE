#!/bin/sh

username="user1"
script="check.sh"

cat $script | ssh ${username}@192.168.2.4 "sh"  #(1)
cat $script | ssh ${username}@192.168.2.5 "sh"
cat $script | ssh ${username}@192.168.2.6 "sh"
