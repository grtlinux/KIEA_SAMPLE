package kiea.priv.zzz.book.JavaDesignPattern.thread.T99_AppendixB.t01.Others.SingleThreadedExecution;

import java.util.Date;

public class MySystem {
    private static MySystem instance = null;
    private Date date = new Date();
    private MySystem() {
    }
    public Date getDate() {
        return date;
    }
    public static synchronized MySystem getInstance() {
        if (instance == null) {
            instance = new MySystem();
        }
        return instance;
    }
}
