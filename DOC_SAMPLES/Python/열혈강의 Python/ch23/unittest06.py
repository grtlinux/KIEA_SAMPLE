# unittest06.py 
import unittest  
  
def suite():  
    return unittest.defaultTestLoader.loadTestsFromNames(('readNumberTest1','readNumberTest2'))  
  
if __name__=='__main__':  
    unittest.main(argv=('','-v','suite'))  
