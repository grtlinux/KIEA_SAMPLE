package kiea.priv.zzz.book.JavaDesignPattern.thread.T00_Introduction1.t01.Others.PrintThread;

public class PrintThread extends Thread {
    private String message;
    public PrintThread(String message) {
        this.message = message;
    }
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.print(message);
        }
    }
}
