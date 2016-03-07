# except01.py
import sys 
 
class Big(Exception): 
    pass 
 
class Small(Big): 
    pass 
 
def dosomething1(): 
    x = Big() 
    raise x 
 
def dosomething2(): 
    raise Small() 
 
for f in (dosomething1, dosomething2): 
    try: 
        f() 
    except Big: 
        print sys.exc_type 
