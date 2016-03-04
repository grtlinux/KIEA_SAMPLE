package kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.A2.activeobject;



class DisplayStringRequest extends MethodRequest {
    private final String string;
    public DisplayStringRequest(Servant servant, String string) {
        super(servant, null);
        this.string = string;
    }
    public void execute() {
        servant.displayString(string);
    }
}
