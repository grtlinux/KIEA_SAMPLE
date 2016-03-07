#!/usr/loca/bin/python
# File : canvasscroll1.pyw

from Tkinter import *

root = Tk()
root.protocol("WM_DELETE_WINDOW", root.destroy)

xbar = Scrollbar(root, orient="horizontal")  # 스크롤바 생성
ybar = Scrollbar(root)  # 스크롤바 생성

canvas = Canvas(root, xscrollcommand=xbar.set, yscrollcommand=ybar.set, confine=1, scrollregion=(0,0,500,500))

xbar.config(command=canvas.xview)
ybar.config(command=canvas.yview)

xbar.pack(side=BOTTOM, fill=X)
ybar.pack(side=RIGHT, fill=Y)

canvas.pack(side=LEFT, fill=BOTH)

id1 = canvas.create_line(0,0,500,500,width=5,fill="green", tags="line")
id2 = canvas.create_oval(50,50,250,250,width=5,fill="red", tags="oval")
id3 = canvas.create_oval(50,50,150,150,width=5,fill="blue", tags="oval2")

#canvas.scale("oval", 0, 0, 2, 2)

root.mainloop()
