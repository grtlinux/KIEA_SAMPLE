package kiea.priv.zzz.book.JavaDesignPattern.thread.T10_TwoPhaseTermination.t01.A4;

public class GracefulThread extends Thread {
    // true
    private volatile boolean shutdownRequested = false;

    // 
    public final void shutdownRequest() {
        shutdownRequested = true;
        interrupt();
    }

    // 
    public final boolean isShutdownRequested() {
        return shutdownRequested;
    }

    // 
    public final void run() {
        try {
            while (!shutdownRequested) {
                doWork();
            }
        } catch (InterruptedException e) {
        } finally {
            doShutdown();
        }
    }

    // 
    protected void doWork() throws InterruptedException {
    }

    // 
    protected void doShutdown() {
    }
}
