package kiea.priv.zzz.book.JavaDesignPattern.thread.T06_ReadWriteLock.t01.A4;

import kiea.priv.zzz.book.JavaDesignPattern.thread.T06_ReadWriteLock.t01.A4.readwritelock.Data;

public class ReaderThread extends Thread {
    private final Data data;
    public ReaderThread(Data data) {
        this.data = data;
    }
    public void run() {
        try {
            while (true) {
                char[] readbuf = (char[])data.read();
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readbuf));
            }
        } catch (InterruptedException e) {
        }
    }
}
