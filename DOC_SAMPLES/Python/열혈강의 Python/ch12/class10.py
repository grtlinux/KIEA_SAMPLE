# class10.py
class SeqFile: 
        def __init__(self, fname): 
                self.f = open(fname) 
        def __getitem__(self, k): 
                line = self.f.readline() 
                if not line: raise IndexError, k 
                return self.filter(line)   # 내가 원하는 필터 적용 
        def filter(self, line):  # 필터 정의 
                return line.rstrip()  # 뒤의 공백을 없앤다. 

s2 = SeqFile('t.txt')
for line in s2: 
        print line,
