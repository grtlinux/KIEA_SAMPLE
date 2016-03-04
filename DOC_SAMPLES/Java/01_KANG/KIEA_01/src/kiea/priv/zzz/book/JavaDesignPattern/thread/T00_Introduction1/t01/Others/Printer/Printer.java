package kiea.priv.zzz.book.JavaDesignPattern.thread.T00_Introduction1.t01.Others.Printer;

public class Printer implements Runnable {
    private String message;
    public Printer(String message) {
        this.message = message;
    }
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.print(message);
        }
    }
}
