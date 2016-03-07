# AUTHOR : GANG SEONG LEE 
# FILE : replace.py 
import sys  # argv를 위해서 사용함 
import re   # 정규식 처리 모듈. subn을 위해서 사용함 
  
def replace(fname, srcstr, deststr): 
    f = open(fname) 
    txt = f.read() 
    txt = re.subn(srcstr, deststr, txt)[0] 
    return txt 
 
if __name__ == '__main__': 
    if len(sys.argv) != 4: 
        print """Usage : replace filename srcstr deststr""" 
        sys.exit() 
    print replace(sys.argv[1], sys.argv[2], sys.argv[3])
