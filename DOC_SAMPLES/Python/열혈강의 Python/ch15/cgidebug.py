#!/usr/local/bin/python
# cgidebug.py
import cgitb; cgitb.enable()

print 'ContentType: html/html\n'

for k in range(10):
    print k
print NonExistingVariable
