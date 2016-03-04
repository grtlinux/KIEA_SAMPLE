#!/bin/sh
# Script name: speller
# Purpose: Check and fix spelling errors in a file
# 
exec < tmp      					   # opens the tmp file 
while read line    # read from the tmp file
do
    echo $line
    echo  -n  "Is this word correct? [Y/N] "
    read answer < /dev/tty   # read from the terminal
    case  "$answer"  in
    [Yy]*) 
	continue;;
    *) 
	echo  "What is the correct spelling? "
	read word < /dev/tty
	sed  "s/$line/$word/g" tmp > error
	mv error tmp
	echo $line has been changed to $word.
    esac
done
