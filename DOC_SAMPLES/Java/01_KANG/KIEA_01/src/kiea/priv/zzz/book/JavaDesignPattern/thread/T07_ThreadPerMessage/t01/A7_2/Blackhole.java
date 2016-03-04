package kiea.priv.zzz.book.JavaDesignPattern.thread.T07_ThreadPerMessage.t01.A7_2;

public class Blackhole {
    public static void enter(Object obj) {
        System.out.println("Step 1");
        magic(obj);
        System.out.println("Step 2");
        synchronized (obj) {
            System.out.println("Step 3 (never reached here)");  // 
        }
    }
    public static void magic(final Object obj) {
        // threadobj
        Thread thread = new Thread() {
            public void run() {
                synchronized (obj) { // obj
                    synchronized (this) {
                        this.notifyAll();   // obj
                    }
                    try {
                        this.join(); // 
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        synchronized (thread) {
            thread.start(); // 
            try {
                thread.wait(); // obj
            } catch (InterruptedException e) {
            }
        }
    }
}
