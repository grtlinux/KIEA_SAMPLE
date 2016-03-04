#!/bin/sh

#(1)
IFS='
'

for filename in $(ls -AF)  #(2)
do
  case "$filename" in  #(3)
  .*/)
    echo "dot directory: $filename"
  ;;
  .*)
    echo "dot file: $filename"
  ;;
  esac
done
