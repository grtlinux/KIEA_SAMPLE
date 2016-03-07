#!/usr/loca/bin/python
#File : listbox1.pyw

from Tkinter import *

def delete():
    items = listbox.curselection()
    try:
        items = map(int, items)
    except ValueError: pass
    print items
    for el in items:
        print listbox.get(el)

root = Tk()

scrollbar = Scrollbar(root)
scrollbar.pack(side=RIGHT, fill=Y)

listbox = Listbox(root, yscrollcommand=scrollbar.set, selectmode=EXTENDED)
for i in range(100):
    listbox.insert(END, str(i))
listbox.pack(side=LEFT, fill=BOTH)

scrollbar.config(command=listbox.yview)

b = Button(root, text="Delete", command=delete)
b.pack()

root.mainloop()
