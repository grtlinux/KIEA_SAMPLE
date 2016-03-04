#!/bin/sh

tar cf archive.tar log

# -9 옵션으로 압축률을 최대로 함
gzip -9 archive.tar  #(1)
