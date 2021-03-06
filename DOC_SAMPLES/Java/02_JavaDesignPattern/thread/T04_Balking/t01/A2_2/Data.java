package kiea.priv.zzz.book.JavaDesignPattern.thread.T04_Balking.t01.A2_2;

import java.io.IOException;
import java.io.FileWriter;
import java.io.Writer;

public class Data {
    private String filename;    // 
    private String content;     // 
    private boolean changed;    // true

    public Data(String filename, String content) {
        this.filename = filename;
        this.content = content;
        this.changed = true;
    }

    // 
    public synchronized void change(String newContent) {        
        content = newContent;                                   
        changed = true;                                           
    }                                                           

    // 
    public void save() throws IOException {   // not synchronized
        if (!changed) {                                           
            System.out.println(Thread.currentThread().getName() + " balks");
            return;                                             
        }                                                       
        doSave();                                             
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        changed = false;                                          
    }                                                           

    // 
    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " calls doSave, content = " + content);
        Writer writer = new FileWriter(filename);
        writer.write(content);
        writer.close();
    }
}
