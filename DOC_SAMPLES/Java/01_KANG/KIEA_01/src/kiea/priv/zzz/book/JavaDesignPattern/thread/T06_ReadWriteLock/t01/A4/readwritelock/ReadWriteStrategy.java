package kiea.priv.zzz.book.JavaDesignPattern.thread.T06_ReadWriteLock.t01.A4.readwritelock;

public interface ReadWriteStrategy {
    public abstract Object doRead() throws InterruptedException;
    public abstract void doWrite(Object arg) throws InterruptedException;
}
