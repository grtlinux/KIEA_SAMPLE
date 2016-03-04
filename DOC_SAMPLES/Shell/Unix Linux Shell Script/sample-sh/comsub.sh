#!/bin/sh

err_count=$(grep -c "ERROR" /var/log/myapp/$(hostname).log)  #(1)
echo "Error counts: $err_count"
