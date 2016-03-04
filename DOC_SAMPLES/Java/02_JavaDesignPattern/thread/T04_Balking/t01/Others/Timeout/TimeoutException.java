package kiea.priv.zzz.book.JavaDesignPattern.thread.T04_Balking.t01.Others.Timeout;

public class TimeoutException extends InterruptedException {
    public TimeoutException(String msg) {
        super(msg);
    }
}
