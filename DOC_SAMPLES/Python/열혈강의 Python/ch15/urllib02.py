# urllib02.py 
from urllib import urlretrieve 
 
def hook(blockNumber, blockSize, totalSize): 
    print 'Downloading %s of %s' % (blockNumber * blockSize, totalSize) 
 
fname, header = urlretrieve('ftp://www.python.org/pub/jython/jython-moin-08-Oct-2001.tgz', 'test.tgz', hook) 
print '%s saved' % fname 
