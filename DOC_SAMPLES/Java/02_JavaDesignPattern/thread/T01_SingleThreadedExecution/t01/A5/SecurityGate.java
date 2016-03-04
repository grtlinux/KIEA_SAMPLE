package kiea.priv.zzz.book.JavaDesignPattern.thread.T01_SingleThreadedExecution.t01.A5;

public class SecurityGate {
    private int counter;
    public void enter() {
        counter++;
    }
    public void exit() {
        counter--;
    }
    public int getCounter() {
        return counter;
    }
}
