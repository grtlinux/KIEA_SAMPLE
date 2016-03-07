# fileinputex.py
import fileinput

for line in fileinput.input():
    # 파일이름, 총 라인번호, 파일 라인번호, 라인 출력
    print '%s %d,%d: %s' % (fileinput.filename(), fileinput.lineno(), fileinput.filelineno(), line),
