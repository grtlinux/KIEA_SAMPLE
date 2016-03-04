package kiea.priv.zzz.book.JavaDesignPattern.thread.T06_ReadWriteLock.t01.Q6;

public final class ReadWriteLock {
    private int readingReaders = 0; // (a) 
    private int writingWriters = 0; // (b) 

    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0) {
            wait();
        }
        readingReaders++;                       // (a) 1
    }

    public synchronized void readUnlock() {
        readingReaders--;                       // (a) 1
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        while (readingReaders > 0 || writingWriters > 0) {
            wait();
        }
        writingWriters++;                       // (b) 1
    }

    public synchronized void writeUnlock() {
        writingWriters--;                       // (b) 1
        notifyAll();
    }
}
