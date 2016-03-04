#!/bin/sh

start_command="./start.sh"

if [ -x "$start_command" ]; then  #(1)
  $start_command
else
  echo "ERROR: -x $start_command failed." >&2
  exit 1
fi
