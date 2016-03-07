# urllib02.py
import urllib 
 
def getpage(url): 
    try: 
        f = urllib.urlopen(url) 
        return f.read() 
    except IOError, msg: 
        print msg, url 
        return None 
 
text = getpage('http://www.python.org/') 
text = getpage('http://dldk.dsfkj.sksd/') 
