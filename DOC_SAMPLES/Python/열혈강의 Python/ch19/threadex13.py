# threadex13.py
from Queue import Queue

class Stack(Queue):
    def _get(self):
        item = self.queue[-1]
        del self.queue[-1]
        return item

s = Stack()
s.put(1)
s.put(2)
s.put(3)
for i in range(3):
    print s.get()
