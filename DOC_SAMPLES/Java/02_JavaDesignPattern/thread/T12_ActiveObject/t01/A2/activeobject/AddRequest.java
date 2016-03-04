package kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.A2.activeobject;



class AddRequest extends MethodRequest {
    private final String x;
    private final String y;
    public AddRequest(Servant servant, FutureResult future, String x, String y) {
        super(servant, future);
        this.x = x;
        this.y = y;
    }
    public void execute() {
        Result result = servant.add(x, y);
        future.setResult(result);
    }
}
