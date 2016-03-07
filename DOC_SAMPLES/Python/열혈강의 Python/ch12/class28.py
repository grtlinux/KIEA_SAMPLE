# class28.py
import UserString 
 
class MyString(UserString.MutableString): 
    def __init__(self, data): 
        UserString.UserString.__init__(self, data) 
 
 
s = MyString('python rules!') 
s[0] = 'P' 
print s 
s[:6] = 'PYTHON' 
print s 
