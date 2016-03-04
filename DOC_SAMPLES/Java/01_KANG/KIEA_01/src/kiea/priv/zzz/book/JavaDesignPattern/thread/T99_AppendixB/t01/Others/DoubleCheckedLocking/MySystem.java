package kiea.priv.zzz.book.JavaDesignPattern.thread.T99_AppendixB.t01.Others.DoubleCheckedLocking;

// 
import java.util.Date;

public class MySystem {
    private static MySystem instance = null;
    private Date date = new Date();
    private MySystem() {
    }
    public Date getDate() {
        return date;
    }
    public static MySystem getInstance() {
        if (instance == null) {                 // (a) test-
            synchronized (MySystem.class) {     // (b) synchronized
                if (instance == null) {         // (c) and-test-
                    instance = new MySystem();  // (d) and-set
                }
            }                                   // (e) synchronized
        }
        return instance;                        // (f)
    }
}
