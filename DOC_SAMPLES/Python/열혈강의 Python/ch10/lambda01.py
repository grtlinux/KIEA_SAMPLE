# lambda01.py
# 더하기, 빼기, 곱하기, 나누기 함수 리스트 정의 
func = [lambda x,y:x+y, lambda x,y:x-y, lambda x,y:x*y, lambda x,y:x/y] 
 
# 메뉴를 표시하고 메뉴를 입력받는다(0-4). 
def menu(): 
    print "0. add" 
    print "1. sub" 
    print "2. mul" 
    print "3. div" 
    print "4. quit" 
    return input('Select menu:') 
 
# 메뉴를 표시하고 선택된 메뉴를 실행한다. 
while 1: 
    sel = menu()                # 메뉴 표시하고 키 입력 받음 
    if sel < 0 or sel > len(func):      # 범위를 벋어나면 다시 메뉴 표시 
        continue 
    if sel == len(func):        # quit 이면 종료 
        break 
    x = input('First operand:') # 첫 인수 읽음 
    y = input('Second operand:')        # 둘째 인수 읽음 
    print 'Result=', func[sel](x, y)    # 해당 함수 호출
