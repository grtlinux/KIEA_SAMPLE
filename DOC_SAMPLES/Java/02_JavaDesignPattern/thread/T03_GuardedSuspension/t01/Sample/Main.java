package kiea.priv.zzz.book.JavaDesignPattern.thread.T03_GuardedSuspension.t01.Sample;

public class Main {
    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "Alice", 3141592L).start();
        new ServerThread(requestQueue, "Bobby", 6535897L).start();
    }
}
