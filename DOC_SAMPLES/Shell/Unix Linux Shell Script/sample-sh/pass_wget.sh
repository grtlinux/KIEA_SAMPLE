#!/bin/sh

username=guest
hostname=localhost

echo -n "Password: "
stty -echo  #(1)
read password  #(2)
stty echo  #(3)

echo

#(4)
wget -q --password=$password ftp://${username}@${hostname}/filename.txt
