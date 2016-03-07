# nested01.py
x = 2           <--- global 
def F():  
    x = 1       <--- 함수 G 안에서 여기는 local도 global도 아니다 
    def G():  
        print x  
    G()  
   
F()
