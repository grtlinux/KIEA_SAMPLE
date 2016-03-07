# class02.py
class MyClass2: 
    def set(self, v): 
        self.value = v 
    def incr(self): 
        self.set(self.value + 1)  # 내부 메써드 호출 
    def put(self): 
        print self.value
