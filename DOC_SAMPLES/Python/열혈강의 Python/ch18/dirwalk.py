# dirwalk.py
import os.path

# 인수, 디렉토리경로명, 파일목록
def dosomething(args, dirname, filenames):
    fnames = filter(lambda fname: fname[-4:] == '.bak', filenames) # .bak로 끝나는 파일명을 fnames에 모은다
    if len(fnames) > 0:       # .bak로 끝나는 파일이 있으면
        print dirname, fnames # 디렉토리 경로명과 파일명을 출력한다

os.path.walk("c:\\Python22", dosomething, [])   # 시작 디렉토리, 호출할 사용자 함수, dosomething의 args로 전달될 인수.
