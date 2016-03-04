package kiea.priv.zzz.book.JavaDesignPattern.thread.T06_ReadWriteLock.t01.A3_1;

public final class ReadWriteLock {
    private int readingReaders = 0; // (A) 
    private int waitingWriters = 0; // (B) 
    private int writingWriters = 0; // (C) 
    private boolean preferWriter = true; // true

    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
            wait();
        }
        readingReaders++;                       // (A) 1
    }

    public synchronized void readUnlock() {
        readingReaders--;                       // (A) 1
        preferWriter = true;
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++;                       // (B) 1
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                wait();
            }
        } finally {
            waitingWriters--;                   // (B) 1
        }
        writingWriters++;                       // (C) 1
    }

    public synchronized void writeUnlock() {
        writingWriters--;                       // (C) 1
        preferWriter = false;
        notifyAll();
    }
}
