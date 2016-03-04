package kiea.priv.zzz.etc.hypericSIGAR.t02;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hyperic.sigar.FileInfo;
import org.hyperic.sigar.SigarException;

public class Ls extends SigarCommandBase
{
	private static boolean flag = true;
	
	public Ls(Shell shell)
	{
		super(shell);
	}
	
	public Ls()
	{
		super();
	}
	
	@Override
	public boolean validateArgs(String[] args)
	{
		return args.length == 1;
	}
	
	@Override
	public String getSyntaxArgs()
	{
		return "[file]";
	}
	
	@Override
	public String getUsageShort()
	{
		return "simple fileinfo test at the moment (like is -l)";
	}
	
	@Override
	public boolean isPidCompleter()
	{
		return true;
	}
	
	@Override
	public void output(String[] args) throws SigarException
	{
		String file = args[0];
		
		FileInfo link = this.sigar.getLinkInfo(file);
		if (link.getType() == FileInfo.TYPE_LNK) {
			try {
				file = file + " -> " + new File(file).getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		FileInfo info = this.sigar.getFileInfo(file);
		println(link.getTypeChar() + info.getPermissionsString()
				+ "\t" + info.getUid()
				+ "\t" + info.getGid()
				+ "\t" + info.getSize()
				+ "\t" + getDate(info.getMtime())
				+ "\t" + file
				);
	}
	
	private String getDate(long mtime)
	{
		final String format = "MMM dd yyyy";
		return new SimpleDateFormat(format).format(new Date(mtime));
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new Ls().processCommand(args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (!flag) test01(args);
		if (flag) test01(new String[] { "D:/KANG.20141230/WORK/top.bat" });
		if (flag) test01(new String[] { "D:/KANG.20141230/WORK/java7doc.lnk" });
	}
}
