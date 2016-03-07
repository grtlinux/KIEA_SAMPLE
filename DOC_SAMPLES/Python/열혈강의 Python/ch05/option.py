# option.py 
import getopt 
import sys 

checkext = 1 
verbose = 1 
maxpage = 500 
 
opts, args = getopt.getopt(sys.argv[1:], 'xvm:') 
 
for o, a in opts: 
    if o == '-x': 
        checkext = not checkext # checkext = 0 
    if o == '-v': 
        verbose = verbose + 1  # verbose += 1 
    if o == '-m': 
        maxpage = int(a)         
print checkext, verbose, maxpage 
