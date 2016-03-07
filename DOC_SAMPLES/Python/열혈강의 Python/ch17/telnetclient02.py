# telnetclient02.py
from telnetlib import Telnet
import sys

class TelnetClient:
    def __init__(self, host):
        self.telnet = Telnet(host)

    def mt_interact(self):
        """Multithreaded version of interact()."""
        import thread
        thread.start_new_thread(self.listener, ())
        while 1:
            line = sys.stdin.readline()
            if not line:
                break
            self.telnet.write(line)

    def listener(self):
        """Helper for mt_interact() -- this executes in the other thread."""
        while 1:
            try:
                data = self.telnet.read_eager()
            except EOFError:
                print '*** Connection closed by remote host ***'
                return
            if data:
                sys.stdout.write(data)
            else:
                sys.stdout.flush()


if __name__ == '__main__':
    tc = TelnetClient('daisy')
    tc.mt_interact()
