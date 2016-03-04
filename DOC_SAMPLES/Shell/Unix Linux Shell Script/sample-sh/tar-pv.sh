#!/bin/sh

DATA_DIR=/myapp/datadir

cd $DATA_DIR   #(1)
tar cf - bigfile1.dat bigfile2.dat | pv | gzip > archive.tar.gz   #(2)
