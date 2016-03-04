#!/bin/sh

# 검색할 에러 메시지
message="Unknown Error"

#(1)
strings -f /home/user1/myapp/bin/* | grep "$message"
