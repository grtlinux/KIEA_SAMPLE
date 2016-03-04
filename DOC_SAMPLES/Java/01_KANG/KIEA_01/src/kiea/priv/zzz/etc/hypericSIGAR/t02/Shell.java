package kiea.priv.zzz.etc.hypericSIGAR.t02;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarLoader;
import org.hyperic.sigar.SigarPermissionDeniedException;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;
import org.hyperic.sigar.cmd.SigarCommandBase;
import org.hyperic.sigar.ptql.ProcessFinder;
import org.hyperic.sigar.shell.ShellBase;
import org.hyperic.sigar.shell.ShellCommandExecException;
import org.hyperic.sigar.shell.ShellCommandHandler;
import org.hyperic.sigar.shell.ShellCommandInitException;
import org.hyperic.sigar.shell.ShellCommandUsageException;
import org.hyperic.sigar.util.Getline;

public class Shell extends ShellBase
{
	private static boolean flag = true;
	
	public static final String RCFILE_NAME = ".sigar_shellrc";
	private static final String CLEAR_SCREEN = "\033[2J";
	
	private Sigar sigar = new Sigar();
	private SigarProxy proxy = SigarProxyCache.newInstance(this.sigar);
	private long[] foundPids = new long[0];
	private boolean isInteractive = false;
	
	public Shell() {}
	
	public static void clearScreen()
	{
		System.out.print(CLEAR_SCREEN);
	}
	
	public SigarProxy getSigarProxy()
	{
		return this.proxy;
	}
	
	public Sigar getSigar()
	{
		return this.sigar;
	}
	
	public boolean isInteractive()
	{
		return this.isInteractive;
	}
	
	public void setInteractive(boolean value)
	{
		this.isInteractive = value;
	}
	
	public void registerCommands() throws ShellCommandInitException
	{
		//registerCommandHeader("df", new Df(this));
		if (SigarLoader.IS_WIN32) {
			//registerCommandHandler("service", new Win32Service(this));
			//registerCommandHandler("fversion", new FileVersionInfo(this));
		}
		
		try {
			// requires junit.jar
			registerCommandHandler("test", "org.hyperic.sigar.test.SigarTestRunner");
		} catch (NoClassDefFoundError e) {
			//
		} catch (Exception e) {
			//
		}
	}
	
	private void registerCommandHandler(String name, String className) throws Exception
	{
		Class<?> cls = Class.forName(className);
		Constructor<?> con = cls.getConstructor(new Class[] { this.getClass() });
		registerCommandHandler(name, (ShellCommandHandler) con.newInstance(new Object[] { this }));
	}
	
	public void processCommand(ShellCommandHandler handler, String[] args) throws ShellCommandUsageException, ShellCommandExecException
	{
		try {
			super.processCommand(handler, args);
			if (handler instanceof SigarCommandBase) {
				((SigarCommandBase) handler).flush();
			}
		} catch (Exception e) {
			// e.printStackTrace();
			// throw e;
		} finally {
			SigarProxyCache.clear(this.proxy);
		}
	}
	
	public static long[] getPids(SigarProxy sigar, String[] args) throws SigarException
	{
		long[] pids;
		
		switch (args.length) {
		case 0:
			pids = new long[] { sigar.getPid() };
			break;
		case 1:
			if (args[0].indexOf("=") > 0) {
				pids = ProcessFinder.find(sigar, args[0]);
			} else if (args[0].equals("$$")) {
				pids = new long[] { sigar.getPid() };
			} else {
				pids = new long[] { Long.parseLong(args[0]) };
			}
			break;
		default:
			pids = new long[args.length];
			for (int i=0; i < args.length; i++) {
				pids[i] = Long.parseLong(args[i]);
			}
			break;
		}
		
		return pids;
	}
	
	public long[] findPids(String[] args) throws SigarException
	{
		if ((args.length == 1) && args[0].equals("-")) {
			return this.foundPids;
		}
		
		this.foundPids = getPids(this.proxy, args);
		
		return this.foundPids;
	}
	
	public long[] findPids(String query) throws SigarException
	{
		return findPids(new String[] { query });
	}
	
	public void readCommandFile(String dir)
	{
		try {
			File rc = new File(dir, RCFILE_NAME);
			readRCFile(rc, false);
			if (this.isInteractive && Getline.isTTY()) {
				this.out.println("Loaded rc file: " + rc);
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	public String getUserDeniedMessage(long pid)
	{
		return SigarPermissionDeniedException.getUserDeniedMessage(this.proxy, pid);
	}
	
	public void shutdown()
	{
		this.sigar.close();;
		// cleanup for dmalloc
		// using reflection increase junit.jar is not present
		
		try {
			// SigarTestCase.closeSigar()
			Class.forName("org.hyperic.sigar.test.SigarTestCase").getMethod("closeSigar", new Class[0]).invoke(null, new Object[0]);
		} catch (ClassNotFoundException e) {
			// SigarTestCase.java not compiled w/o junit.jar
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (NoClassDefFoundError e) {
			// avoiding possible class not found: junit/framework/testcase
		}
		super.shutdown();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			
			Shell shell = new Shell();
			
			try {
				if (args.length == 0) {
					shell.isInteractive = true;
				}
				
				shell.init("sigar", System.out, System.err);
				shell.registerCommands();
				
				shell.readCommandFile(System.getProperty("user.home"));
				shell.readCommandFile(".");
				shell.readCommandFile(SigarLoader.getLocation());
				
				if (shell.isInteractive) {
					shell.initHistory();
					Getline.setCompleter(shell);
					shell.run();
				} else {
					shell.handleCommand(null, args);
				}
				
			} catch (Exception e) {
				// e.printStackTrace();
				// throw e;
				System.err.println("Unexpected exception: " + e);
			} finally {
				shell.shutdown();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (flag) test01(args);
	}
}
