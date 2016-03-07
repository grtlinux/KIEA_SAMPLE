#File : message1.pyw

from Tkinter import *
import tkMessageBox

filename = "nonexistingfile"
try:
    fp = open(filename)
except:
    tkMessageBox.showwarning(
        "화일 열기",
        "다음 화일을 열 수 없음\n(%s)" % (filename,)
    )
