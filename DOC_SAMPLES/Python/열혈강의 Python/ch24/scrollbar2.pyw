#File : scrollbar2.pyw

from Tkinter import *

root = Tk()

scrollbar = Scrollbar(root)
scrollbar.pack(side=RIGHT, fill=Y)

listbox = Listbox(root, yscrollcommand=scrollbar.set)
for i in range(100):
    listbox.insert(END, str(i))
listbox.pack(side=LEFT, fill=BOTH)

scrollbar.config(command=listbox.yview)

b = Button(root, text="Delete", command=lambda lb=listbox: lb.delete(ACTIVE))
b.pack()

listbox.delete(1)

root.mainloop()
