#!/bin/sh

tmpfile="tmp.$$"  #(1)

date > $tmpfile  #(2)
sleep 10

cat $tmpfile  #(3)
rm -f $tmpfile
