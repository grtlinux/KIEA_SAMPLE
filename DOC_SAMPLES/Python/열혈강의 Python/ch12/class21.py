# class21.py
import math  
  
class Point:  
    def __init__(self, x, y):  
        self.x = x  
        self.y = y  
    def area(self): # 점의 면적은 0 이다 
        return 0 
    def move(self, dx, dy):  
        self.x += dx  
        self.y += dy  
    def __repr__(self):  
        return 'x=%s y=%s' % (self.x, self.y)  
  
class Circle(Point):  
    def __init__(self, x, y, r):  
        Point.__init__(self, x, y)  
        self.radius = r  
    def area(self):  
        return math.pi * self.radius * self.radius  
    def __repr__(self):  
        return '%s radius=%s' % (Point.__repr__(self), self.radius)  
  
class Cylinder(Circle):  
    def __init__(self, x, y, r, h):  
        Circle.__init__(self, x, y, r)  
        self.height = h  
    def area(self): # 원주의 표면적. 위아래의 원의 면적 + 기둥의 표면적 
        return 2 * Circle.area(self) + 2 * math.pi * self.radius * self.height  
    def volume(self): # 체적 
        return Circle.area(self) * self.height  
    def __repr__(self):  
        return '%s height=%s' % (Circle.__repr__(self), self.height)  
  
if __name__ == '__main__': 
    p1 = Point(3, 5)  
    c1 = Circle(3, 4, 5)  
    c2 = Cylinder(3, 4, 5, 6)  
    print p1  
    print c1  
    print c2  
    print c2.area(), c2.volume()  
    print c1.area()  
    p1.move(10, 10)  
    print p1
