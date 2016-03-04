package kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.A2.activeobject;



public interface ActiveObject {
    public abstract Result makeString(int count, char fillchar);
    public abstract void displayString(String string);
    public abstract Result add(String x, String y);
}
