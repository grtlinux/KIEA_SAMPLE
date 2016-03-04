package kiea.priv.zzz.book.JavaDesignPattern.thread.T04_Balking.t01.Others.Init1;

public class Something {
    private boolean initialized = false;
    public synchronized void init() {
        if (initialized) {
            return;
        }
        doInit();
        initialized = true;
    }
    private void doInit() {
        // 
    }
}
