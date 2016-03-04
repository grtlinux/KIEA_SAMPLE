package kiea.priv.zzz.book.JavaDesignPattern.thread.T09_Future.t01.A3.content;



public class Retriever {
    public static Content retrieve(final String urlstr) {
        final AsyncContentImpl future = new AsyncContentImpl();

        new Thread() {
            public void run() {
                future.setContent(new SyncContentImpl(urlstr));
            }
        }.start();

        return future;
    }
}
