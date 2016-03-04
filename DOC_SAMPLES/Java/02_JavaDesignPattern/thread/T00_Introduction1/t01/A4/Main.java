package kiea.priv.zzz.book.JavaDesignPattern.thread.T00_Introduction1.t01.A4;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("A Bad Bank", 1000);   // 
        new ClientThread(bank).start();
        new ClientThread(bank).start();
    }
}
