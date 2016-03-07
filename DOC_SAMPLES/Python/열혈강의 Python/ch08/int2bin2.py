# file : int2bin2.py

octtab = {'0':'000', '1':'001', '2':'010', '3':'011',
          '4':'100', '5':'101', '6':'110', '7':'111'}

def bin2(n,width=0):
    result=[]
    while 1:
        result[:0]=[str(n&1)]
        n>>=1
        if not n:
            break
    results=''.join(result)
    if not width:
        width=len(results)
    return results.zfill(width)[:width]

print bin2(23, 7)  # 10진수 23을 2진수 7자리로
