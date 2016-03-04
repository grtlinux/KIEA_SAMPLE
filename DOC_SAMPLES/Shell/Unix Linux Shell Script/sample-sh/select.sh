#!/bin/sh

while :
do
  echo "Menu:"
  echo "1) list file"
  echo "2) current directory"
  echo "3) exit"

  read number  #(1)
  case $number in  #(2)
    1)
      ls
      ;;
    2)
      pwd
      ;;
    3)
      exit
      ;;
    *)  #(3)
      echo "Error: Unknown Command"
      ;;
  esac

  echo
done
