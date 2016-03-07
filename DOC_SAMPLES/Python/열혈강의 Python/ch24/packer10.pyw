# File: packer10.pyw

from Tkinter import *

root = Tk()

root.configure(width=130, height=100)
widget = [None] * 3

widget[0] = Button(root, text="Widget-1")
widget[1] = Button(root, text="Widget--2")
widget[2] = Button(root, text="Widget---3")

y = 0
for el in widget:
    el.place(x=y, y=y, width=70, height=25)
    y = y + 25

root.mainloop()
