#!/usr/bin/python

print "Content-Type: text/html\n\n"

html_header = '''
<HTML>
<HEAD></HEAD>
<BODY> '''

html_footer = '''
</BODY>
</HTML> '''

print html_header
for k in range(10):
        print k, '<br>'
print html_footer
