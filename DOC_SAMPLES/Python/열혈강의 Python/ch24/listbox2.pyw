#!/usr/loca/bin/python
#File : listbox1.pyw

from Tkinter import *

root = Tk()

scrollbar = Scrollbar(root)
scrollbar.pack(side=RIGHT, fill=Y)

listbox1 = Listbox(root, yscrollcommand=scrollbar.set, exportselection=0)
listbox2 = Listbox(root, yscrollcommand=scrollbar.set, exportselection=0)
for i in range(100):
    listbox1.insert(END, str(i))
    listbox2.insert(END, str(i+100))
listbox1.pack(side=LEFT, fill=BOTH)
listbox2.pack(side=LEFT, fill=BOTH)

scrollbar.config(command=listbox1.yview)

root.mainloop()
