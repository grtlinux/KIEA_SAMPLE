package kiea.priv.zzz.book.JavaDesignPattern.thread.T11_ThreadSpecificStorage.t01.Sample2;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TSLog {
    private PrintWriter writer = null;

    // writer
    public TSLog(String filename) {
        try {
            writer = new PrintWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 
    public void println(String s) {
        writer.println(s);
    }

    // 
    public void close() {
        writer.println("==== End of log ====");
        writer.close();
    }
}
