package kiea.priv.zzz.book.JavaDesignPattern.thread.T07_ThreadPerMessage.t01.A7_1;

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
        // thread
        Thread thread = new Thread() {      // inner class
            public void run() {
                synchronized (obj) { // obj
                    synchronized (this) {
                        this.setName("Locked"); // 
                        this.notifyAll();       // obj
                    }
                    while (true) {
                        // 
                    }
                }
            }
        };
        synchronized (thread) {
            thread.setName("");
            thread.start(); // 
            // Guarded Suspension
            while (thread.getName().equals("")) {
                try {
                    thread.wait(); // obj
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
