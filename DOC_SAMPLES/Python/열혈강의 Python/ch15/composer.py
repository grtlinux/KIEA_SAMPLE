#! /usr/local/bin/python
import cgi

def getCheckList():
        form = cgi.FieldStorage()
        try:
                checkList = form['composer']
        except:
                return []
        if type(checkList) == type([]):
                removeList = [x.value for x in checkList]
        else:
                removeList = [checkList.value]
        return removeList

if __name__ == '__main__':
        print 'Content-type: text/plain\n\n'
        checklist = getCheckList()
        print '선택하신 좋아하는 작곡가는 다음과 같습니다.'
        for item in checklist:
                print item
