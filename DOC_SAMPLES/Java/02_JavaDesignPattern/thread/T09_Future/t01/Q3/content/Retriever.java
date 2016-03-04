package kiea.priv.zzz.book.JavaDesignPattern.thread.T09_Future.t01.Q3.content;



public class Retriever {
    public static Content retrieve(String urlstr) {
        return new SyncContentImpl(urlstr);
    }
}
