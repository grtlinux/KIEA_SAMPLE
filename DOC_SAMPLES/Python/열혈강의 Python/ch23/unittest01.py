# unittest01.py
import unittest

# method 1
class ArithmeticTestCase(unittest.TestCase):
    def runTest(self):  # 기본 테스트 메써드
        self.assertEquals( (3 * 4), 11)	# 실패할 테스트. 12 와 11은 다르다

test = ArithmeticTestCase()
runner = unittest.TextTestRunner()
runner.run(test)
