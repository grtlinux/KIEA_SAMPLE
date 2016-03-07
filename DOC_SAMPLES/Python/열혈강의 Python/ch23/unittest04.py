# unittest04.py
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

def method3Test2():
    testSuite = unittest.TestSuite()
    for testmethod in ('testAdd', 'testMul'):
        testSuite.addTest(ArithmeticTestCase3(testmethod))

    runner = unittest.TextTestRunner()
    runner.run(testSuite)

def method3Test4():
    testSuite = unittest.makeSuite(ArithmeticTestCase3, 'test')

    runner = unittest.TextTestRunner()
    runner.run(testSuite)

def method3Test5():
    suite1 = unittest.makeSuite(ArithmeticTestCase3, 'test')
    suite2 = unittest.makeSuite(ArithmeticTestCase4, 'test')
    allTestSuite = unittest.TestSuite((suite1, suite2))

    runner = unittest.TextTestRunner()
    runner.run(allTestSuite)

#method3Test2()
