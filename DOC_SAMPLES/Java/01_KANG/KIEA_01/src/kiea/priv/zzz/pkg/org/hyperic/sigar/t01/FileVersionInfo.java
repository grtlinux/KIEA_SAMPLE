package kiea.priv.zzz.pkg.org.hyperic.sigar.t01;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.win32.FileVersion;
import org.hyperic.sigar.win32.Win32;

public class FileVersionInfo extends SigarCommandBase
{
    public FileVersionInfo(Shell shell) {
        super(shell);
    }

    public FileVersionInfo() {
        super();
    }

    protected boolean validateArgs(String[] args) {
        return args.length >= 1;
    }

    public String getUsageShort() {
        return "Display file version info";
    }

    public void output(String[] args) throws SigarException {
        for (int i=0; i<args.length; i++) {
            String exe = args[i];
            if (new File(exe).exists()) {
                output(exe);
            }
            else {
                long[] pids = this.shell.findPids(exe);
                for (int j=0; j<pids.length; j++) {
                    try {
                        output(sigar.getProcExe(pids[j]).getName());
                    } catch (SigarException e) {
                        println(exe + ": " + e.getMessage());
                    }
                }
            }
        }
    }

    private void output(String key, String val) {
        final int max = 20;
        int len = max - key.length();
        StringBuffer sb = new StringBuffer();
        sb.append("  ").append(key);
        while (len-- > 0) {
            sb.append('.');
        }
        sb.append(val);
        println(sb.toString());
    }

    public void output(String exe) throws SigarException {
        FileVersion info = Win32.getFileVersion(exe);
        if (info == null) {
            return;
        }
        println("Version info for file '" + exe + "':");
        output("FileVersion", info.getFileVersion());
        output("ProductVersion", info.getProductVersion());
        for (@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, String>> it = ((Map<String, String>) info.getInfo()).entrySet().iterator(); it.hasNext();)
        {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            output(entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) throws Exception {
        new FileVersionInfo().processCommand(args);
    }

}
