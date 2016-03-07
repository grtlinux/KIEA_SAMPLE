#! /usr/local/bin/python
import cgi

def upload(form, fname, file):
        try:
                filename = form[fname].value
                filestr = form[file].value
        except:
                return None
        #f = open(filename, 'wb')
        #f.write(filestr)
        return 1

if __name__ == '__main__':
        print 'Content-type: text/plain\n\n'
        form = cgi.FieldStorage()
        if upload(form, 'filename', 'file'):
                print '전송했습니다'
        else:
                print '실패했습니다.'
