package kiea.priv.zzz.book.JavaDesignPattern.thread.T02_Immutable.t01.A5_1;

public class Point {
    public int x;
    public int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
