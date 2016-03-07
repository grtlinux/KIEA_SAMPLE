# file : profilex.py
def count1000():
    for x in range(1000):
        pass

def count10000():
    for x in range(10000):
        pass

def fast():
    count1000()
    count1000()
    count1000()

def slow():
    count10000()
    count10000()

def spam():
    fast()
    slow()

if __name__ == '__main__':
    import profile
    profile.run('spam()')
#    profile.run('spam()', 'spamprofile.txt')