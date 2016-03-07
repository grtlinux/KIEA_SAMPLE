# File: packer6.pyw

from Tkinter import *

root = Tk()

widget = [None] * 3

widget[0] = Button(root, text="Widget1")
widget[1] = Button(root, text="Widget2")
widget[2] = Button(root, text="Widget3")

for el in widget:
    el.pack(side=TOP, fill=X)

button1 = Button(root, text="Button---1")
button1.pack(after=widget[1], fill=X)
button2 = Button(root, text="Button---2")
button2.pack(before=widget[0], fill=X)

root.mainloop()
