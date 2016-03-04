#!/bin/sh

result="invalid value"

if [ "$result" = "invalid value" ]; then  #(1)
  echo "ERROR: $result"  1>&2
  exit 1
fi
