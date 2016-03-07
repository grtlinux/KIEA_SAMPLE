#!/usr/loca/bin/python
# menu3.pyw

from Tkinter import *

def menu_exit(event=None):
    root.quit()
    root.destroy()

def event_handler(event=None):
    print 'keycode=',event.keycode
    print 'num=',event.num
    print 'keysym=',event.keysym
    print 'keysym_num=',event.keysym_num
    print 'state=',event.state
    print 'char=',event.char
    print
#    for el in dir(event):
#        print el, '=', getattr(event, el)

root = Tk()

# create a menu
menu = Menu(root)
root.config(menu=menu)

filemenu = Menu(menu)
menu.add_cascade(label="File", underline=0, menu=filemenu)
filemenu.add_command(label="Exit", underline=1, accelerator="Alt+X ", command=menu_exit)

root.bind("<Key>", event_handler)

root.mainloop()
