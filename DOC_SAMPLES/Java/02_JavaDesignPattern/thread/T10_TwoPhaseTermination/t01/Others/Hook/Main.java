package kiea.priv.zzz.book.JavaDesignPattern.thread.T10_TwoPhaseTermination.t01.Others.Hook;

public class Main {
    public static void main(String[] args) {
        System.out.println("main:BEGIN");

        // shutdown hook
        Runtime.getRuntime().addShutdownHook(
            new Thread() {
                public void run() {
                    System.out.println("*****");
                    System.out.println(Thread.currentThread().getName() + ": SHUTDOWN HOOK!");
                    System.out.println("*****");
                }
            }
        );

        System.out.println("main:SLEEP...");

        // 3
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        System.out.println("main:EXIT");

        // 
        System.exit(0);

        // 
        System.out.println("main:END");
    }
}
