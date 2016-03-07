# File: button1.py

from Tkinter import *

class App:

    def __init__(self, master):

        frame = Frame(master)
        frame.pack()

        self.button = Button(frame, text="QUIT", fg="red", command=frame.quit)
        self.button.pack(side=LEFT)

        self.w = Label(master, text="Hello, world!")
        self.w.pack()
        
        self.var = IntVar()
        self.var.set(1)
        c = Checkbutton(frame, text="Expand", variable=self.var, command=self.cb)
        c.pack()

    def cb(self):
        if self.var.get() == 1:
            self.w.configure(text='Variable is Set')
        else:
            self.w.configure(text='Variable is Reset')

root = Tk()

app = App(root)

root.mainloop()
