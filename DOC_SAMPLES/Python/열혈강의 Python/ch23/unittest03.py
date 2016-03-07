# unittest03.py
import unittest

class ArithmeticTestCase3(unittest.TestCase):
    def setUp(self):
        print 'setUp called..'

    def testAdd(self):
        print 'testAdd..'
        self.assertEquals( 3 + 4, 7)

    def testMul(self):
        print 'testMul..'
        self.assertEquals( 3 * 4, 12)

    def tearDown(self):
        print 'tearDown called..'

runner = unittest.TextTestRunner()
for testmethod in ('testAdd', 'testMul'):
    test = ArithmeticTestCase3(testmethod)
    runner.run(test)
