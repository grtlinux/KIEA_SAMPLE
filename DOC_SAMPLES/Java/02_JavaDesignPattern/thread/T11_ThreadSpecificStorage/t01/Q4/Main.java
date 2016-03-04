package kiea.priv.zzz.book.JavaDesignPattern.thread.T11_ThreadSpecificStorage.t01.Q4;

public class Main {
    public static void main(String[] args) {
        new ClientThread("Alice").start();
        new ClientThread("Bobby").start();
        new ClientThread("Chris").start();
    }
}
