package kiea.priv.zzz.book.JavaDesignPattern.thread.T03_GuardedSuspension.t01.A3;

public class Request {
    private final String name;
    public Request(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String toString() {
        return "[ Request " + name + " ]";
    }
}
