# File: packer7.pyw

from Tkinter import *

root = Tk()

widget = [None] * 3

widget[0] = Button(root, text="Widget1")
widget[1] = Button(root, text="Widget2")
widget[2] = Button(root, text="Widget3")

for el in widget:
    el.pack(side=TOP, fill=X, padx=5, pady=10)

root.mainloop()
