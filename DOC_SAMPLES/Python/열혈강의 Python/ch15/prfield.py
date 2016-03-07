#!/usr/bin/python
 
import cgi   
 
print "Content-Type: text/plain\n\n"
form = cgi.FieldStorage()
for name in form.keys():
    print "Input: " + name + " value: " + form[name].value
print "Finished!"
