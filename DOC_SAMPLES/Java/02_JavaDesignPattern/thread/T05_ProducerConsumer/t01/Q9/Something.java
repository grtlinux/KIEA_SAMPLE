package kiea.priv.zzz.book.JavaDesignPattern.thread.T05_ProducerConsumer.t01.Q9;

public class Something {
    public static void method(long x) throws InterruptedException {
        if (x != 0) {
            Object object = new Object();
            synchronized (object) {
                object.wait(x);
            }
        }
    }
}
