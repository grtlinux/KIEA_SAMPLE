#!/usr/loca/bin/python
from Tkinter import *

root = Tk()
canvas = Canvas(root)
canvas.pack()
xy = 10,10,150,90
canvas.create_arc(xy, start=0,extent=270,fill="red", style="pieslice")
canvas.create_arc(xy, start=270,extent=60,fill="blue", style="pieslice")
canvas.create_arc(xy, start=330,extent=30,fill="green", style="pieslice")
