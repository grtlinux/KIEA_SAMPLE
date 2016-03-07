# generator03.py
from __future__ import generators 
def odds(limit=None): 
        k = 1 
        while not limit or limit >= k: 
                yield k 
                k += 2 
 
for k in odds(20): 
    print k, 
 
print list(odds(20)) 
