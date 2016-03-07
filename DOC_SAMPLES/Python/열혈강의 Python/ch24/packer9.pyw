# File: packer9.pyw

from Tkinter import *

root = Tk()

widget = [None] * 3

widget[0] = Button(root, text="Widget-1")
widget[1] = Button(root, text="Widget--2")
widget[2] = Button(root, text="Widget---3")

#N, E, W, S, NE, SE, NW, SW, CENTER
widget[0].pack(anchor=NW)
widget[1].pack(anchor=NE)
widget[2].pack(anchor=CENTER)

root.mainloop()
