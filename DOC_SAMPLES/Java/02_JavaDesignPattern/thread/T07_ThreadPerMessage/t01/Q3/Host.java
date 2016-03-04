package kiea.priv.zzz.book.JavaDesignPattern.thread.T07_ThreadPerMessage.t01.Q3;

import kiea.priv.zzz.book.JavaDesignPattern.thread.T07_ThreadPerMessage.t01.Sample.Helper;

public class Host {
    private final Helper helper = new Helper();
    public void request(final int count, final char c) {
        System.out.println("    request(" + count + ", " + c + ") BEGIN");
        new Thread() {                      
            public void run() {             
                helper.handle(count, c);    
            }                               
        }.run();
        System.out.println("    request(" + count + ", " + c + ") END");
    }
}
