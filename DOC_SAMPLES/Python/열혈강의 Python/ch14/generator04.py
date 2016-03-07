# generator04.py
from __future__ import generators  
class Tree(object):
    def __init__(self, data=None, left=None, right=None):  
        self.data = data  	# 노드의 값
        self.left = left  	# 왼쪽 노드
        self.right = right  	# 오른쪽 노드

    def inorder(self):		# inorder 탐색
        if self.left:
            for x in self.left.inorder():
                yield x
        yield self
        if self.right:
            for x in self.right.inorder():
                yield x

    def __iter__(self):  	# 반복자로 inorder 발생자 객체를 넘겨준다!
        return self.inorder()

    def __repr__(self, level=0, indent="    "): 
        s = level*indent + `self.data` 
        if self.left: 
            s = s + "\n" + self.left.__repr__(level+1, indent) 
        if self.right: 
            s = s + "\n" + self.right.__repr__(level+1, indent) 
        return s 

def tree(list):
    n = len(list) 
    if n == 0: 
       return None 
    i = n / 2 
    return Tree(list[i], tree(list[:i]), tree(list[i+1:]))

if __name__ == '__main__':
    t = tree('abcdef')
    print t
    print
    for el in t.inorder():
        print el.data,
