# file : dec2any.py

# n : 10진수
# base : 변환할 진수
# width : 출력 문자열 폭. 0이면 기본 값
def int2digit(n, base, width=0):
    res = ''
    while n > 0:
        n, r = divmod(n, base)
        if r > 9:
            r = chr(ord('a')+r-10)
        res = str(r) + res
    if not res: res = '0'
    if not width:
        width = len(res)
    return res.zfill(width)[:width]

print int2digit(70, 5)    # 10진수 70을 5진수로 변환
print int2digit(70, 12)   # 10진수 70을 12진수로 변환
print int2digit(70, 15)   # 10진수 70을 15진수로 변환
print int2digit(70, 16)   # 10진수 70을 16진수로 변환
print int2digit(70, 2, 8) # 10진수 70을 2진수로 변환. 최소 8자리에 맞춤
