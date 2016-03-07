#!/usr/loca/bin/python
# menu1.pyw

from Tkinter import *

def print_msg(msg):
    w.configure(text=msg)
    
def menu_new():
    print_msg("New called")

def menu_open():
    print_msg("Open called")

def menu_about():
    print_msg("About called")

def menu_exit():
    root.quit()
    root.destroy()
    
root = Tk()

# create a menu
menu = Menu(root)
root.config(menu=menu)

w = Label(root, text="Hello, world!", width=50, height=3)
w.pack()

filemenu = Menu(menu)
menu.add_cascade(label="File", menu=filemenu)
filemenu.add_command(label="New", command=menu_new)
filemenu.add_command(label="Open...", command=menu_open)
filemenu.add_separator()
filemenu.add_command(label="Exit", command=menu_exit)

helpmenu = Menu(menu)
menu.add_cascade(label="Help", menu=helpmenu)
helpmenu.add_command(label="About...", command=menu_about)

root.mainloop()
