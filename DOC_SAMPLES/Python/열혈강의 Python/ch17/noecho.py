# noecho.py
import termios,TERMIOS,os,select,string,sys

def read1():
oldattr = termios.tcgetattr(0)
try:
	attr = termios.tcgetattr(0)
	attr[2] = (attr[2] & ~TERMIOS.NLDLY) | TERMIOS.NL0
	attr[3] = attr[3] & ~(TERMIOS.ICANON|TERMIOS.ECHO)
	termios.tcsetattr(0,TERMIOS.TCSANOW,attr)
	return os.read(0,1)
finally:
	termios.tcsetattr(0,TERMIOS.TCSANOW,oldattr)

ch = read1()
