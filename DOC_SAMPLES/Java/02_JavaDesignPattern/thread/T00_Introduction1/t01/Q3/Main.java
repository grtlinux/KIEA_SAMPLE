package kiea.priv.zzz.book.JavaDesignPattern.thread.T00_Introduction1.t01.Q3;

public class Main {
    public static void main(String[] args) {
        new PrintThread("*").run();
        new PrintThread("+").run();
    }
}
