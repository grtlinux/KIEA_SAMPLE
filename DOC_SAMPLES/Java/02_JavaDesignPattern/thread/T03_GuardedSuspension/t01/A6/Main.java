package kiea.priv.zzz.book.JavaDesignPattern.thread.T03_GuardedSuspension.t01.A6;

public class Main {
    public static void main(String[] args) {
        // 
        RequestQueue requestQueue = new RequestQueue();
        Thread alice = new ClientThread(requestQueue, "Alice", 314159L);
        Thread bobby = new ServerThread(requestQueue, "Bobby", 265358L);
        alice.start();
        bobby.start();

        // 10
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }

        // interrupt
        System.out.println("***** calling interrupt *****");
        alice.interrupt();
        bobby.interrupt();
    }
}
