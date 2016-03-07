#!/usr/loca/bin/python
#File : dialog1.pyw

from Tkinter import *

def ok():
    print "Value is", entry.get()
    top.destroy()

root = Tk()
top = Toplevel(root)

label = Label(top, text="Value")
label.pack(side=LEFT)
entry = Entry(top)
entry.pack(padx=5)
button = Button(top, text="OK", command=ok) 
button.pack()
top.title("Dialog Window")
top.transient(root)

root.wait_window(top)
