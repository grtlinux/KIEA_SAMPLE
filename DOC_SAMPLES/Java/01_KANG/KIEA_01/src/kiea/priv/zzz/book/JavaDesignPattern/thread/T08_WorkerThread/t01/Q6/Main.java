package kiea.priv.zzz.book.JavaDesignPattern.thread.T08_WorkerThread.t01.Q6;

import kiea.priv.zzz.book.JavaDesignPattern.thread.T08_WorkerThread.t01.A6.Channel;
import kiea.priv.zzz.book.JavaDesignPattern.thread.T08_WorkerThread.t01.A6.ClientThread;

public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel(5);   // 
        channel.startWorkers();
        ClientThread alice = new ClientThread("Alice", channel);
        ClientThread bobby = new ClientThread("Bobby", channel);
        ClientThread chris = new ClientThread("Chris", channel);
        alice.start();
        bobby.start();
        chris.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        alice.stopThread();
        bobby.stopThread();
        chris.stopThread();
        channel.stopAllWorkers();
    }
}
