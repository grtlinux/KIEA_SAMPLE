package kiea.priv.zzz.book.JavaDesignPattern.thread.T03_GuardedSuspension.t01.A6;

import java.util.LinkedList;

public class RequestQueue {
    private final LinkedList queue = new LinkedList();
    public synchronized Request getRequest() throws InterruptedException {
        while (queue.size() <= 0) {
            wait();
        }
        return (Request)queue.removeFirst();
    }
    public synchronized void putRequest(Request request) {
        queue.addLast(request);
        notifyAll();
    }
}
