# doctest01.py
'''
이것은 문서 문자열입니다.
어떤 설명을 여기다 달아도 좋습니다.
하지만 대화식 실행 코드는 doctest에 의해서 실행됩니다.

>>> add(1, 3)
4
>>> add('spam', 'code')
'spamcode'
'''

def add(a, b):
    '''
    >>> add([1,2,3], [4,5,6])
    [1, 2, 3, 4, 5, 6]
    '''
    return a + b

def _test():
    import doctest, add
    return doctest.testmod(add)

if __name__ == "__main__":
    _test()
