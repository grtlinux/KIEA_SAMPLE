package kiea.priv.zzz.etc.hypericSIGAR.t01;

import java.util.Iterator;
import java.util.Map;

import org.hyperic.sigar.SigarException;

public class ShowEnv extends SigarCommandBase
{
    public ShowEnv(Shell shell) {
        super(shell);
    }

    public ShowEnv() {
        super();
    }

    protected boolean validateArgs(String[] args) {
        return true;
    }

    public String getUsageShort() {
        return "Show process environment";
    }

    public boolean isPidCompleter() {
        return true;
    }

    public void output(String[] args) throws SigarException {
        long[] pids = this.shell.findPids(args);

        for (int i=0; i<pids.length; i++) {
            try {
                println("pid=" + pids[i]);
                output(pids[i]);
            } catch (SigarException e) {
                println(e.getMessage());
            }
            println("\n------------------------\n");
        }
    }

    public void output(long pid) throws SigarException {
        @SuppressWarnings("unchecked")
		Map<String, String> env = (Map<String, String>) this.proxy.getProcEnv(pid);

        for (Iterator<Map.Entry<String, String>> it = env.entrySet().iterator(); it.hasNext();)
        {
            Map.Entry<String, String> ent = (Map.Entry<String, String>)it.next();

            println(ent.getKey() + "=" + ent.getValue());
        }
    }

    public static void main(String[] args) throws Exception {
        new ShowEnv().processCommand(args);
    }
}
