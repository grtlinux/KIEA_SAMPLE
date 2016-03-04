package kiea.priv.zzz.book.JavaDesignPattern.thread.T09_Future.t01.Q4;

public class Host {
    public Data request(final int count, final char c) {
        System.out.println("    request(" + count + ", " + c + ") BEGIN");

        // (1) FutureData
        final FutureData future = new FutureData();

        // (2) RealData
        new Thread() {                                      
            public void run() {                             
                RealData realdata = new RealData(count, c);
                future.setRealData(realdata);
            }                                               
        }.start();                                          

        System.out.println("    request(" + count + ", " + c + ") END");

        // (3) FutureData
        return future;
    }
}
