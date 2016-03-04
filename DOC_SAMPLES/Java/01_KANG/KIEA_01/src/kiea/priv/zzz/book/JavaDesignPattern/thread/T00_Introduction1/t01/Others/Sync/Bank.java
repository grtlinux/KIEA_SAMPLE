package kiea.priv.zzz.book.JavaDesignPattern.thread.T00_Introduction1.t01.Others.Sync;

public class Bank {
    private int money;
    private String name;

    public Bank(String name, int money) {
        this.name = name;
        this.money = money;
    }

    // 
    public synchronized void deposit(int m) {
        money += m;
    }

    // 
    public synchronized boolean withdraw(int m) {
        if (money >= m) {
            money -= m;
            return true;    // 
        } else {
            return false;   // 
        }
    }

    public String getName() {
        return name;
    }
}
