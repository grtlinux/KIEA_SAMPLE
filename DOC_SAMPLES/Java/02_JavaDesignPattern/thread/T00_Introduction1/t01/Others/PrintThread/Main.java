package kiea.priv.zzz.book.JavaDesignPattern.thread.T00_Introduction1.t01.Others.PrintThread;

public class Main {
    public static void main(String[] args) {
        new PrintThread("Good!").start();
        new PrintThread("Nice!").start();
    }
}
