package kiea.priv.zzz.book.JavaDesignPattern.thread.T00_Introduction1.t01.A4;

public class ClientThread extends Thread {
    private Bank bank;
    public ClientThread(Bank bank) {
        this.bank = bank;
    }
    public void run() {
        while (true) {
            boolean ok = bank.withdraw(1000);
            if (ok) {
                bank.deposit(1000);
            }
        }
    }
}
