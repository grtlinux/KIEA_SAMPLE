package kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.Sample;

import kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.Sample.activeobject.ActiveObject;
import kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.Sample.activeobject.ActiveObjectFactory;

public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread("Alice", activeObject).start();
        new MakerClientThread("Bobby", activeObject).start();
        new DisplayClientThread("Chris", activeObject).start();
    }
}
