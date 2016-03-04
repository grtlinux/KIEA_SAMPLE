#!/bin/sh

tty=`tty`  #(3)
while read question  #(1)
do
  echo $question
  read dir < $tty  #(4)
  echo "Command: ls $dir"
  ls $dir
done < question.txt  #(2)
