package kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.A3_2.activeobject;



class SearchRequest extends MethodRequest {
    private final String word;
    private final Display display;
    public SearchRequest(Servant servant, String word, Display display) {
        super(servant, null);
        this.word = word;
        this.display = display;
    }
    public void execute() {
        servant.search(word, display);
    }
}
