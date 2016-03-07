#!/usr/loca/bin/python
#File : canvasevent.pyw

from Tkinter import *

def curpos(event):
    global x, y, item
    x = event.x
    y = event.y
    item = canvas.find_closest(x,y)

def moveitem(event):
    global x, y
    nx = event.x
    ny = event.y
    distx = nx - x
    disty = ny - y
    canvas.move(item, distx, disty)
    x = nx
    y = ny

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

line = canvas.create_line(0,0,500,500,width=5,fill="green")
circle = canvas.create_oval(50,50,150,150,width=5,fill="blue")

canvas.bind("<Button-1>", curpos)
canvas.bind("<B1-Motion>", moveitem)

root.mainloop()
