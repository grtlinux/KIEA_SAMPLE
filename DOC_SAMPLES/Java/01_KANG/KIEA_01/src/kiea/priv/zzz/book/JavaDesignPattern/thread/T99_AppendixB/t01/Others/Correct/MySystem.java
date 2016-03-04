package kiea.priv.zzz.book.JavaDesignPattern.thread.T99_AppendixB.t01.Others.Correct;

import java.util.Date;

public class MySystem {
    private static MySystem instance = new MySystem();
    private Date date = new Date();
    private MySystem() {
    }
    public Date getDate() {
        return date;
    }
    public static MySystem getInstance() {
        return instance;
    }
}
