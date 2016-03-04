package kiea.priv.zzz.book.JavaDesignPattern.thread.T04_Balking.t01.Q5;

public class Main {
    public static void main(String[] args) {
        Thread thread = new TestThread();
        while (true) {
            thread.start();
        }
    }
}
