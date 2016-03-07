# urllib01.py 
from urllib import urlretrieve 
 
fname, header = urlretrieve('ftp://www.python.org/pub/jython/jython-moin-08-Oct-2001.tgz', 'test.tgz') 
print '%s saved' % fname 
