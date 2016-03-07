# text1.pyw

from Tkinter import *

def menu_underline():
    st,ed = text.tag_ranges(SEL)
    text.tag_add('mytmp1', st, ed)
    text.tag_config("mytmp1", underline=1)

def menu_color():
    st,ed = text.tag_ranges(SEL)
    text.tag_add('mytmp2', st, ed)
    text.tag_config("mytmp2", background="yellow")

def menu_big():
    st,ed = text.tag_ranges(SEL)
    text.tag_add('mytmp3', st, ed)
    text.tag_config("mytmp3", font="Arial 24")

def menu_exit(event=None):
    root.quit()
    root.destroy()

root = Tk()

# create a menu
menu = Menu(root)
root.config(menu=menu)

text = Text(root)
text.pack()

filemenu = Menu(menu)
menu.add_cascade(label="File", underline=0, menu=filemenu)
filemenu.add_command(label="Exit", underline=1, accelerator="Alt+X ", command=menu_exit)

editmenu = Menu(menu)
menu.add_cascade(label="Edit", underline=0, menu=editmenu)
editmenu.add_command(label="Underline", underline=0, command=menu_underline)
editmenu.add_command(label="Color", underline=0, command=menu_color)
editmenu.add_command(label="Big", underline=0, command=menu_big)

root.bind("<Alt-Key-x>", menu_exit)

text.insert(END, """The Last Empress, a Broadway style musical seasoned with Asian exotica, remarkable
elegance and splendor is ready to capture again a wide audience. Premiered in 1995, after
four years' production period and starring Korea's most superb cast and staff, this grand
Korean musical is certainly a rare pleasure. Since its successful Broadway debut in 1997,
The Last Empress has received worldwide much applause from the enthusiastic audience
echoed by the major international news media: 
    "Enchanting both visually and acoustically by the elegant stage effect, gorgeous
costume and vivacious music together with the superb acting..." New York Times

    "Korean version of Evita which conveys the magnificent messages so vividly..." L.A.
Times
""")

root.mainloop()
