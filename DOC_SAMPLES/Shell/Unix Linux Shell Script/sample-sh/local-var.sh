#!/bin/sh

DIR=/var/tmp

ls_home()
{
  local DIR  #(1)

  DIR=~/$1  #(2)
  echo "directory: $DIR"
  ls $DIR
}

ls_home logdir  #(3)

echo "directory: $DIR"   #(4)
ls $DIR
