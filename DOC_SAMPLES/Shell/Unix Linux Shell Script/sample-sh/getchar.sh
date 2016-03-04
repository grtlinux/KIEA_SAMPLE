#!/bin/sh

echo -n "Type Your Answer [y/n]: "

tty_state=`stty -g`  #(1)
stty raw  #(2)
char=`dd bs=1 count=1 2> /dev/null`  #(3)
stty "$tty_state"   #(4)

echo

case "$char" in  #(5)
  [yY])
    echo "Input: YES"
    ;;
  [nN])
    echo "Input: NO"
    ;;
  *)
    echo "Input: What?"
    ;;
esac
