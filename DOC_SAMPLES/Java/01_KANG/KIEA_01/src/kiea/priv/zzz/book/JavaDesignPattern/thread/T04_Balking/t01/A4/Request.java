package kiea.priv.zzz.book.JavaDesignPattern.thread.T04_Balking.t01.A4;

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
