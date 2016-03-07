#! /usr/local/bin/python

import os
import Cookie

print 'Content-Type: text/plain\n\n'

c = Cookie.SimpleCookie()
c.load(os.environ["HTTP_COOKIE"])
print c.keys()
print c['name']
print c['name'].value
