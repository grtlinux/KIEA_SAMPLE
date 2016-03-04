package kiea.priv.zzz.etc.hypericSIGAR.t02;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.win32.FileVersion;
import org.hyperic.sigar.win32.Win32;

public class FileVersionInfo extends SigarCommandBase
{
	private static boolean flag = true;
	
	public FileVersionInfo(Shell shell)
	{
		super(shell);
	}
	
	public FileVersionInfo()
	{
		super();
	}
	
	@Override
	public boolean validateArgs(String[] args)
	{
		return args.length >= 1;
	}
	
	@Override
	public String getSyntaxArgs()
	{
		return "[args]";
	}
	
	@Override
	public String getUsageShort()
	{
		return "Display file version info";
	}
	
	@Override
	public boolean isPidCompleter()
	{
		return true;
	}
	
	@Override
	public void output(String[] args) throws SigarException
	{
		if (flag) {
			// 확인출력
			for (String arg : args) {
				System.out.println(">" + arg);
			}
		}
		
		for (int i=0; i < args.length; i++) {
			String exe = args[i];
			if (new File(exe).exists()) {
				output(exe);
			} else {
				long[] pids = this.shell.findPids(exe);
				for (int j=0; j < pids.length; j++) {
					try {
						output(sigar.getProcExe(pids[j]).getName());
					} catch (SigarException e) {
						System.out.println(exe + ": " + e.getMessage());
					}
				}
			}
		}
	}
	
	private void output(String key, String val)
	{
		final int max = 20;
		int len = max - key.length();
		StringBuffer sb = new StringBuffer();
		sb.append("   ").append(key);
		while (len-- > 0) {
			sb.append('.');
		}
		sb.append(val);
		System.out.println(sb.toString());
	}
	
	private void output(String exe) throws SigarException
	{
		FileVersion info = Win32.getFileVersion(exe);
		if (info == null) {
			System.out.println("not file version");
			return;
		}
		
		println("Version info for file '" + exe + "':");
		output("FileVersion", info.getFileVersion());
		output("ProductVersion", info.getProductVersion());
		
		if (!flag) {
			@SuppressWarnings("unchecked")
			Map<String, String> map = info.getInfo();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				output(entry.getKey(), entry.getValue());
			}
		} else {
			Map<?, ?> map = info.getInfo();
			for (Entry<?, ?> entry : map.entrySet()) {
				output((String) entry.getKey(), (String) entry.getValue());
			}
		}
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new FileVersionInfo().processCommand(args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (!flag) test01(args);
		if (flag) test01(new String[] { "D:/KANG.20141230/PROG/Python/Python33/python.exe" });
	}
}
