#!/usr/loca/bin/python
# menu2.pyw

from Tkinter import *

def print_msg(msg):
    text.insert(INSERT, msg)
    
def menu_new(event=None):
    print_msg("New called")

def menu_open(event=None):
    print_msg("Open called")

def menu_about(event=None):
    print_msg("About called")

def menu_exit(event=None):
    root.quit()
    root.destroy()
    

root = Tk()

# create a menu
menu = Menu(root)
root.config(menu=menu)

text = Text(root, width=80, height=20)
text.pack()

filemenu = Menu(menu)
menu.add_cascade(label="File", underline=0, menu=filemenu)
filemenu.add_command(label="New", underline=0)
filemenu.add_command(label="Open...", underline=0)
filemenu.add_separator()
filemenu.add_command(label="Exit", underline=1, accelerator="Alt+X ", command=menu_exit)

editmenu = Menu(menu)
menu.add_cascade(label="Edit", underline=0, menu=editmenu)
editmenu.add_command(label="Cut", underline=2)
editmenu.add_command(label="Copy", underline=0)
editmenu.add_command(label="Paste", underline=0)
editmenu.add_command(label="Select All", underline=7)

helpmenu = Menu(menu)
menu.add_cascade(label="Help", underline=0, menu=helpmenu)
helpmenu.add_command(label="About...", command=menu_about)

root.bind("<Control-n>", menu_new)
root.bind("<Control-o>", menu_open)
root.bind("<Alt-Key-x>", menu_exit)

root.mainloop()
