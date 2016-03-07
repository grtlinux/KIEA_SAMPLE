# class05.py 
class MyString: 
    def __init__(self, str): 
        self.str = str 
    def __div__(self, sep):     # 나누기 연산자 / 가 사용되었을 때 호출되는 함수 
        return self.str.split(sep)      # 문자열 self.str을 sep를 기준으로 분리 

m = MyString("abcdabcdabcd")
print m / "b"       # 문자열을 "b"를 이용해서 리스트로 분리한다.
print m / "bc"      # 문자열을 "bc"를 이용해서 리스트로 분리한다.
