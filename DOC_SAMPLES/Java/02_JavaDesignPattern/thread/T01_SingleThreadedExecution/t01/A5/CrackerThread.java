package kiea.priv.zzz.book.JavaDesignPattern.thread.T01_SingleThreadedExecution.t01.A5;

public class CrackerThread extends Thread {
    private SecurityGate gate;
    public CrackerThread(SecurityGate gate) {
        this.gate = gate;
    }
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            gate.enter();
            gate.exit();
        }
    }
}
