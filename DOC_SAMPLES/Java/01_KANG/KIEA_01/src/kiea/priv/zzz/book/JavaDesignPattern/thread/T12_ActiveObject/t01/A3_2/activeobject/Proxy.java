package kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.A3_2.activeobject;



class Proxy implements ActiveObject {
    private final SchedulerThread scheduler;
    private final Servant servant;
    public Proxy(SchedulerThread scheduler, Servant servant) {
        this.scheduler = scheduler;
        this.servant = servant;
    }
    public void search(String word, Display display) {
        scheduler.invoke(new SearchRequest(servant, word, display));
    }
}
