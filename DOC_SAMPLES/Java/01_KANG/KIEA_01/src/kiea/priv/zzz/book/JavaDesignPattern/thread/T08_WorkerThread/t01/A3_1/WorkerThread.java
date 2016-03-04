package kiea.priv.zzz.book.JavaDesignPattern.thread.T08_WorkerThread.t01.A3_1;

public class WorkerThread extends Thread {
    private final Channel channel;
    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }
    public void run() {
        while (true) {
            Request request = channel.takeRequest();
            request.execute();
        }
    }
}