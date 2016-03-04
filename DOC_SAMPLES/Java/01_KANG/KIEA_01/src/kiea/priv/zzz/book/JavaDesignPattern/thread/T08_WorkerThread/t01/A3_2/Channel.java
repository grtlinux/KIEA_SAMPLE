package kiea.priv.zzz.book.JavaDesignPattern.thread.T08_WorkerThread.t01.A3_2;

public final class Channel {
    public Channel(int threads) {
    }
    public void startWorkers() {
    }
    public void putRequest(final Request request) {
        new Thread() {
            public void run() {
                request.execute();
            }
        }.start();
    }
}
