package kiea.priv.zzz.book.JavaDesignPattern.thread.T05_ProducerConsumer.t01.A9;

public class Main {
    public static void main(String[] args) {
        System.out.println("BEGIN");
        try {
            Something.method(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("END");
    }
}
