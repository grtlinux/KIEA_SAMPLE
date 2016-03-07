# re01.py 
import urllib 
import re  
p = re.compile('<\s*script.*?>(.*?)</\s*script\s*>', re.I | re.S)  
  
def extrScript(url):  
    f = urllib.urlopen(url)   # 웹에 있는 문서를 연다 
    s = f.read()              # 문서를 가져온다 
    script = p.findall(s)     # 스크립트 코드만 추출하여 리스트로 리턴한다 
    return script  
  
if __name__ == '__main__': 
    L = extrScript('http://www.daum.net/')  
    for s in L:  
        print '//', '-' * 60  
        print s  
