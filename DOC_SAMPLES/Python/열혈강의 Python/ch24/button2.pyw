#!/usr/loca/bin/python
# File: button2.pyw

from Tkinter import *

class App:
    def __init__(self, master):

        frame = Frame(master)
        frame.pack()

        MODES = [("Monochrome", "1"),
            ("Grayscale", "L"),
            ("True color", "RGB"),
            ("Color separation", "CMYK")]
    
        # 스트링 변수 생성    
        self.v = StringVar()
        self.v.set("L") # 초기값 설정
    
        for text, mode in MODES: # MODES의 각 터플에 대해서 라디오 버튼 생성
            b = Radiobutton(frame, text=text,
                            variable=self.v, value=mode, indicatoron=0, command=self.rb)
            b.pack(anchor=W, fill='x')

        self.w = Label(master, text=self.v.get())
        self.w.pack()
        
    def rb(self):
        self.w.configure(text=self.v.get())

root = Tk()
app = App(root)
root.mainloop()
