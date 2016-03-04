#!/bin/sh

# 병렬로 여러 사이트에서 내려받기
# 각각 백그라운드에서 처리
#(1)
curl -sO http://www.example.org/download/bigfile.dat &
curl -sO http://www.example.com/files/sample.pdf &
curl -sO http://jp.example.net/images/large.jpg &
