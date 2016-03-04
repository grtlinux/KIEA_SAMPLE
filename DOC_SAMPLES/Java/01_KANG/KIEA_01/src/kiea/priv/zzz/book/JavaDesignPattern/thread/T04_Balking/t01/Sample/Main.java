package kiea.priv.zzz.book.JavaDesignPattern.thread.T04_Balking.t01.Sample;

public class Main {
    public static void main(String[] args) {
        Data data = new Data("data.txt", "(empty)");
        new ChangerThread("ChangerThread", data).start();
        new SaverThread("SaverThread", data).start();
    }
}
