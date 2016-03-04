package kiea.priv.zzz.etc.hypericSIGAR.t02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.hyperic.sigar.FileInfo;
import org.hyperic.sigar.FileTail;
import org.hyperic.sigar.FileWatcherThread;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class Tail
{
	private static boolean flag = true;
	
	public boolean follow;
	public int number = 20;
	public List<String> files = new ArrayList<String>();
	
	public void parseArgs(String[] args) throws SigarException
	{
		for (int i=0; i < args.length; i++) {
			String arg = args[i];
			if (arg.charAt(0) != '-') {
				/*
				 * '-'로 시작되는 문자열이 아니면 파일명이다.
				 */
				this.files.add(arg);
				continue;
			}
			
			/*
			 * '-'로 시작되는 옵션 명령어이다.
			 */
			arg = arg.substring(1);
			if (arg.equals("f")) {
				/*
				 * 연속옵션
				 */
				this.follow = true;
			} else if (Character.isDigit(arg.charAt(0))) {
				/*
				 * 출력라인 옵션
				 */
				this.number = Integer.parseInt(arg);
			} else {
				/*
				 * 모르는 옵션
				 */
				throw new SigarException("Unknown argument : " + args[i]);
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws SigarException
	{
		if (flag) {
			Sigar sigar = new Sigar();
			
			FileWatcherThread watcherThread = FileWatcherThread.getInstance();
			
			watcherThread.doStart();
			
			watcherThread.setInterval(1000);
			
			FileTail watcher = new FileTail(sigar) {
				public void tail(FileInfo info, Reader reader) {
					BufferedReader bufferedReader = new BufferedReader(reader);
					
					if (getFiles().size() > 1) {
						System.out.println("==> " + info.getName() + " <==");
					}
					
					try {
						String line;
						while ((line = bufferedReader.readLine()) != null) {
							System.out.println(line);
						}
						if (flag) System.err.println("end line..");
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			
			for (int i=0; i < args.length; i++) {
				watcher.add(args[i]);
			}
			
			watcherThread.add(watcher);
			
			try {
				System.in.read();
			} catch (IOException e) {}
			
			watcherThread.doStop();
		}
	}
	
	public static void main(String[] args) throws SigarException
	{
		if (!flag) test01(new String[] { "D:/TEMP/temp1.log", "D:/TEMP/temp2.log" } );
		if (flag) test01(new String[] { "D:/TEMP/temp1.log" } );
	}
}
