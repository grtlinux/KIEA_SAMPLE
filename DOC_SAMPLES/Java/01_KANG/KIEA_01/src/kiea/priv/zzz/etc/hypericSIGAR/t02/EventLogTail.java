package kiea.priv.zzz.etc.hypericSIGAR.t02;

import org.hyperic.sigar.win32.EventLog;
import org.hyperic.sigar.win32.EventLogNotification;
import org.hyperic.sigar.win32.EventLogRecord;
import org.hyperic.sigar.win32.EventLogThread;
import org.hyperic.sigar.win32.Win32Exception;

public class EventLogTail
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unused")
	private static void tail(String name, Tail tail) throws Win32Exception
	{
		if (flag) {
			EventLog log = new EventLog();
			log.open(name);
			int max = log.getNumberOfRecords();
			if (tail.number < max) {
				max = tail.number;
			}
			int last = log.getNewestRecord() + 1;
			int first = last - max;
			
			for (int i=first; i < last; i++) {
				EventLogRecord record = log.read(i);
				System.out.println(record);
			}
			log.close();
		}
	}
	
	private static class TailNotification implements EventLogNotification
	{
		@Override
		public void handleNotification(EventLogRecord event)
		{
			System.out.println(event);
		}

		@Override
		public boolean matches(EventLogRecord event)
		{
			return true;
		}
	}
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			Tail tail = new Tail();
			tail.parseArgs(args);
			
			if (!flag) System.out.println("1>" + EventLog.SYSTEM);
			
			if (tail.files.size() < 1) {
				tail.files.add(EventLog.SYSTEM);
			}
			
			for (int i=0; i < tail.files.size(); i++) {
				String name = tail.files.get(i);
				if (flag) System.out.println("2>" + name);
				
				//tail(name, tail);
				if (flag) {
					String[] names = EventLog.getLogNames();
					for (String str : names) {
						System.out.println("3>" + str);
					}

					EventLog log = new EventLog();
					log.open(name);
					int max = log.getNumberOfRecords();
					if (tail.number < max) {
						max = tail.number;
					}
					int last = log.getNewestRecord() + 1;
					int first = last - max;
					
					for (int j=first; j < last; j++) {
						EventLogRecord record = log.read(j);
						System.out.println(record);
					}
					log.close();
				}
				
				if (tail.follow) {
					EventLogThread thread = EventLogThread.getInstance(name);
					thread.add(new TailNotification());
					thread.doStart();
				}
			}
			
			if (tail.follow) {
				System.in.read();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (!flag) test01(new String[] { "D:/TEMP/temp1.log", "D:/TEMP/temp2.log" } );
		if (flag) test01(new String[] { "D:/TEMP/temp1.log" } );
	}
}
