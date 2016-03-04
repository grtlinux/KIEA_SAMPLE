#!/bin/sh
set -u   #(1)

COPY_DIR=/myapp/work

# COPY_DIR이 아니라 COP_DIR이라고 실수했다!
cp myapp.log $COP_DIR
