#!/bin/sh

quote="[\"']"   #(1)
match="[^\"']*"  #(2)

while read line
do
  #(3)
  href=`expr "$line" : ".*href=${quote}\(${match}\)${quote}.*"`
  if [ $? -eq 0 ]; then
    echo $href
  fi
done < index.html
