package kiea.priv.zzz.book.JavaDesignPattern.thread.T07_ThreadPerMessage.t01.A6;

import java.io.IOException;

public class Main {
    public static void main(String args[]) {
        try {
            new MiniServer(8888).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
