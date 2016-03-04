package kiea.priv.zzz.book.JavaDesignPattern.thread.T06_ReadWriteLock.t01.A4;

import kiea.priv.zzz.book.JavaDesignPattern.thread.T06_ReadWriteLock.t01.A4.readwritelock.Data;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new WriterThread(data, "ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
        new WriterThread(data, "abcdefghijklmnopqrstuvwxyz").start();
    }
}
