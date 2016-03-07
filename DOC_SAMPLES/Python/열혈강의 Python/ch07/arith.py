# arith.py
def add(a,b):
    return a+b

def sub(a,b):
    return a-b

def mul(a,b):
    return a*b

def div(a,b):
    return a/b

def choose_menu():
    print 'What do you want to do?'
    print 'add, sub, mul, div, quit'
    return raw_input('Your choice : ')

# 키는 연산의 이름, 값은 함수 객체
menu = {'add':add, 'sub':sub, 'mul':mul, 'div':div}
choice = choose_menu()
while choice != 'quit':
    if menu.has_key(choice):
        x = input('first value  : ')
        y = input('second value : ')
        print menu[choice](x,y)
    choice = choose_menu()
