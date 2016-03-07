import MySQLdb

db = MySQLdb.connect(db='test')
cursor = db.cursor()

# 테이블 생성 
cursor.execute('''CREATE TABLE pet (
	name VARCHAR(20),
	owner VARCHAR(20),
	species VARCHAR(20),
	sex CHAR(1),
	birth DATE,
	death DATE)''')

# 테이블 확인하기
cursor.execute('SHOW TABLES')
print cursor.description

# 데이터 삽입하기
data = """
'Fluffy',  'Harold',  'cat',  'f',  '1993-02-04',  NULL
'Claws',  'Gwen',  'cat',  'm',  '1994-03-17',  NULL 
'Buffy',  'Harold',  'dog',  'f',  '1989-05-13',  NULL
'Fang',  'Benny',  'dog',  'm',  '1990-08-27',  NULL
'Bowser',  'Diane',  'dog',  'm',  '1998-08-31',  '1995-07-29'
'Chirpy',  'Gwen',  'bird',  'f',  '1998-09-11',  NULL
'Whistler',  'Gwen',  'bird',  'f',  '1997-12-09',  NULL 
'Slim',  'Benny',  'snake',  'm',  '1996-04-29',  NULL
"""

for line in data.split('\n'):
	line = line.strip()
	if line == '': continue
	cursor.execute("INSERT INTO pet VALUES(%s)" % line)

# 데이터 검색하기
cursor.execute('select * from pet')

# 질의 결과 각 필드의 특성 알아보기
print cursor.description

# 질의 결과 레코드 수 알아보기
print cursor.rowcount

# 질의 결과 가져오기
rec = cursor.fetchone()  # 결과 한 개 가져오기
print rec
recs = cursor.fetchmany(3) # 결과 3개 가져오기
for rec in recs:
	print rec
recs = cursor.fetchall()  # 결과 모두 가져오기
for rec in recs:
	print rec


# 사전 형식으로 자료 참조하기
cursor.close()
cursor = db.cursor(MySQLdb.cursors.DictCursor)
cursor.execute('select * from pet')
rec = cursor.fetchone()  # 결과 한 개 가져오기
print rec
recs = cursor.fetchmany(3) # 결과 3개 가져오기
for rec in recs:
	print rec

# 테이블 없애기
cursor.execute('DROP TABLE pet')
cursor.close()
