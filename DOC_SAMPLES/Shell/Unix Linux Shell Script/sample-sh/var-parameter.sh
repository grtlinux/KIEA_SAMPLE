#!/bin/sh

cp largefile.tar.gz ${TMPDIR:=/tmp}  #(1)
cd $TMPDIR
tar xzf largefile.tar.gz

echo "Extract files to $TMPDIR."
