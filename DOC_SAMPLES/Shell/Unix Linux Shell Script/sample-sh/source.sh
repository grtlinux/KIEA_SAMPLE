#!/bin/sh

. ./env.sh  #(1)

nowtime
cp -i -v large-file.tar.gz "$WORK_DIR"
nowtime
