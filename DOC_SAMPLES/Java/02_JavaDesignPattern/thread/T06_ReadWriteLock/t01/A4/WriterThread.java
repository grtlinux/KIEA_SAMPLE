package kiea.priv.zzz.book.JavaDesignPattern.thread.T06_ReadWriteLock.t01.A4;

import java.util.Random;

import kiea.priv.zzz.book.JavaDesignPattern.thread.T06_ReadWriteLock.t01.A4.readwritelock.Data;

public class WriterThread extends Thread {
    private static final Random random = new Random();
    private final Data data;
    private final String filler;
    private int index = 0;
    public WriterThread(Data data, String filler) {
        this.data = data;
        this.filler = filler;
    }
    public void run() {
        try {
            while (true) {
                Character c = nextchar();
                data.write(c);
                Thread.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException e) {
        }
    }
    private Character nextchar() {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()) {
            index = 0;
        }
        return new Character(c);
    }
}
