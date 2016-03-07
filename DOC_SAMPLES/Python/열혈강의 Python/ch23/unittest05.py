# unittest05.py
import unittest
import operator

class ArithmeticTestCase5(unittest.TestCase):
    def testAdd(self):
        self.assertEquals( operator.add(3, 4), 7)

    def testSub(self):
        self.assertEquals( operator.sub(3, 4), -1)


class ArithmeticTestCase6(unittest.TestCase):
    def testMul(self):
        self.assertEquals( operator.mul(3, 4), 12)

    def testDiv(self):
        self.assertEquals( operator.div(3, 4), 0)

if __name__ == '__main__':
    unittest.main(argv=('', '-v'))
