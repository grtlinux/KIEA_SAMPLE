# class30.py
import sys, cmd 
 
class MyCmd(cmd.Cmd): 
    def __init__(self): 
        cmd.Cmd.__init__(self) 
        self.prompt = "--> " 
        self.list = [] 
    def do_add(self, x):                # add 명령 
        if x and (x not in self.list): 
            self.list.append(x) 
    def help_add(self):         # add 도움말 
        print 'help for add' 
    def do_show(self, x):       # show 명령 
        print self.list 
    def help_show(self):                # show 도움말 
        print 'help for show' 
    def do_EOF(self, x):                # EOF 키가 입력되었을 때 (Ctrl-Z 또는 Ctrl-D) 
        sys.exit() 
    def help_EOF(self):         # EOF 키 도움말 
        print 'quit the program' 
 
if __name__ == '__main__': 
    c = MyCmd() 
    c.cmdloop()
