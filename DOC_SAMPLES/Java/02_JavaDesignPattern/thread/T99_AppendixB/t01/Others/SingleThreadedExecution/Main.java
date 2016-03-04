package kiea.priv.zzz.book.JavaDesignPattern.thread.T99_AppendixB.t01.Others.SingleThreadedExecution;

public class Main extends Thread {
    public static void main(String[] args) {
        new Main().start();
        new Main().start();
    }
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" + MySystem.getInstance().getDate());
    }
}
