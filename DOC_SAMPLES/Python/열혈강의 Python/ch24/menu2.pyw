#!/usr/loca/bin/python
# menu2.pyw

from Tkinter import *

def print_msg(msg):
    w.configure(text=msg)
    
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

w = Label(root, text="Hello, world!", width=50, height=3)
w.pack()

filemenu = Menu(menu)
menu.add_cascade(label="File", underline=0, menu=filemenu)
filemenu.add_command(label="New", underline=0, accelerator="Ctrl+N", command=menu_new)
filemenu.add_command(label="Open...", underline=0, accelerator="Ctrl+O", command=menu_open)
filemenu.add_separator()
filemenu.add_command(label="Exit", underline=1, accelerator="Alt+X ", command=menu_exit)

helpmenu = Menu(menu)
menu.add_cascade(label="Help", underline=0, menu=helpmenu)
helpmenu.add_command(label="About...", command=menu_about)

root.bind("<Control-n>", menu_new)
root.bind("<Control-o>", menu_open)
root.bind("<Alt-Key-x>", menu_exit)

root.mainloop()
