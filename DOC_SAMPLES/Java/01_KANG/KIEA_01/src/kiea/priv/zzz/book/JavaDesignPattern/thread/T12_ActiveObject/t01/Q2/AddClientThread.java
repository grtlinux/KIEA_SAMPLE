package kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.Q2;

import kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.A2.activeobject.ActiveObject;
import kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.A2.activeobject.Result;

public class AddClientThread extends Thread {
    private final ActiveObject activeObject;
    private String x = "1";
    private String y = "1";
    public AddClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }
    public void run() {
        try {
            for (int i = 0; true; i++) {
                // 
                Result result = activeObject.add(x, y);
                Thread.sleep(100);
                String z = (String)result.getResultValue();
                System.out.println(Thread.currentThread().getName() + ": " + x + " + " + y + " = " + z);
                x = y;
                y = z;
            }
        } catch (InterruptedException e) {
        }
    }
}
