package kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.Sample;

import kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.Sample.activeobject.ActiveObject;

public class DisplayClientThread extends Thread {
    private final ActiveObject activeObject;
    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }
    public void run() {
        try {
            for (int i = 0; true; i++) {
                // 
                String string = Thread.currentThread().getName() + " " + i;
                activeObject.displayString(string);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
        }
    }
}
