package kiea.priv.zzz.book.JavaDesignPattern.thread.T00_Introduction1.t01.Others.TwoThreads;

public class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.print("Nice!");
        }
    }
}
