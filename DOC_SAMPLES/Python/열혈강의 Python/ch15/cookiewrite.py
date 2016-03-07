#! /usr/local/bin/python

import Cookie

c = Cookie.SimpleCookie()
c['name'] = 'gslee'
print c
print 'Content-Type: text/plain\n\n'
print '쿠키를 썼습니다.'
