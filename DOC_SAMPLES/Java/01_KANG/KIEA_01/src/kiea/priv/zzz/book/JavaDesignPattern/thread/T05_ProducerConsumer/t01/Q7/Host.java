package kiea.priv.zzz.book.JavaDesignPattern.thread.T05_ProducerConsumer.t01.Q7;

public class Host {
    public static void execute(int count) {
        for (int i = 0; i < count; i++) {
            doHeavyJob();
        }
    }
    private static void doHeavyJob() {
        // 
        // 
        // 10
        System.out.println("doHeavyJob BEGIN");
        long start = System.currentTimeMillis();
        while (start + 10000 > System.currentTimeMillis()) {
            // busy loop
        }
        System.out.println("doHeavyJob END");
    }
}
