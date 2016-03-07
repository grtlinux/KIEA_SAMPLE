# unittest02.py
import unittest

class ArithmeticTestCase2(unittest.TestCase):
    def setUp(self):
        self.a = 3
        self.b = 4
        self.res = self.a * self.b

    def runTest(self):  # 기본 테스트 메써드
        self.assertEquals( (self.a * self.b), self.res)

    def tearDown(self):
        'do something to clear up some environment'
        pass

test = ArithmeticTestCase2()

runner = unittest.TextTestRunner()
runner.run(test)
