#!/bin/sh
a_flag=0  #(1)
separator=""

while getopts "ap:" option  #(2)
do
  case $option in
    a)
      a_flag=1  #(3)
      ;;
    p)
      separator="$OPTARG"  #(4)
      ;;
    \?)  #(5)
      echo 1>&2 "Usage: getopts.sh [-a] [-p separator] target_dir"
      exit 1
      ;;
  esac
done

shift `expr $OPTIND - 1`   #(6)
path="$1"  #(7)

if [ $a_flag -eq 1 ]; then  #(8)
  ls -a -- $path
else
  ls -- $path
fi

if [ -n "$separator" ]; then  #(9)
    echo "$separator"
fi
