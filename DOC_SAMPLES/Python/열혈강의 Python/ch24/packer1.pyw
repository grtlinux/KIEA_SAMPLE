# File: packer1.pyw

from Tkinter import *

root = Tk()
widget1 = Button(root, text="Widget1")
widget2 = Button(root, text="Widget2")
widget3 = Button(root, text="Widget3")
widget1.pack(side=LEFT)
widget2.pack(side=LEFT)
widget3.pack(side=LEFT)
root.mainloop()
