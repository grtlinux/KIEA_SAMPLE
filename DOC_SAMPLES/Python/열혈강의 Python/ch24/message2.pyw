#File : message1.pyw

from Tkinter import *
import tkMessageBox


if tkMessageBox.askyesno("Print", "Print this report?"):
    tkMessageBox.showinfo("Select", "Yes selected")
else:
    tkMessageBox.showinfo("Select", "No selected")
