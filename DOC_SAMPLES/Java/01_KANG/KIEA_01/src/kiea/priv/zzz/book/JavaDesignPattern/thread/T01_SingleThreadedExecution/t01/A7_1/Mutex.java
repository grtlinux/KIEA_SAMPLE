package kiea.priv.zzz.book.JavaDesignPattern.thread.T01_SingleThreadedExecution.t01.A7_1;

public final class Mutex {
    private boolean busy = false;
    public synchronized void lock() {
        while (busy) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        busy = true;
    }
    public synchronized void unlock() {
        busy = false;
        notifyAll();
    }
}
