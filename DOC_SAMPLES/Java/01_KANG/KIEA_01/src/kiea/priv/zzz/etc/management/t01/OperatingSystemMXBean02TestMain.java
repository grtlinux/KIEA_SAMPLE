package kiea.priv.zzz.etc.management.t01;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;

/**
 * 
 * @author KangSeok
 * 
 * 참조 소스
 * 
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.reactor.schedule.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

// 해당 JVM의 CPU사용량을 체크한다.

public final class JvmCpuUseage implements Executor
{
	private TaskExecutor taskExecutor;
	private long refreshInterval = 5000L;

	public JvmCpuUseage( TaskExecutor taskExecutor )
	{
		this.taskExecutor = taskExecutor;
	}

	public JvmCpuUseage( TaskExecutor taskExecutor , long refreshInterval )
	{
		this.taskExecutor = taskExecutor;
		this.refreshInterval = refreshInterval;
	}

	@Override
	public void execute( )
	{
		taskExecutor.execute( new JvmCpuUseageTask( refreshInterval ) );
	}

	private class JvmCpuUseageTask implements Runnable
	{
		private boolean      stopped;
		private long      refreshInterval;
		private Map< Long , ThreadTime > threadTimeMap = new HashMap< Long , ThreadTime >( );
		private ThreadMXBean    threadBean  = ManagementFactory.getThreadMXBean( );
		private OperatingSystemMXBean  opBean   = ManagementFactory.getOperatingSystemMXBean( );

		public JvmCpuUseageTask( long refreshInterval )
		{
			this.refreshInterval = refreshInterval;
		}

		@Override
		public void run( )
		{
			while ( stopped == false )
			{
				Set< Long > mappedIds;
				synchronized ( threadTimeMap )
				{
					mappedIds = new HashSet< Long >( threadTimeMap.keySet( ) );
				}
				long[] allThreadIds = threadBean.getAllThreadIds( );
				removeDeadThreads( mappedIds , allThreadIds );
				mapNewThreads( allThreadIds );
				Collection< ThreadTime > values;
				synchronized ( threadTimeMap )
				{
					values = new HashSet< ThreadTime >( threadTimeMap.values( ) );
				}
				for ( ThreadTime threadTime : values )
				{
					synchronized ( threadTime )
					{
						threadTime.setCurrent( threadBean.getThreadCpuTime( threadTime.getId( ) ) );
					}
				}
				LOGGER.info( "AvarageUsagePerCPU:{}" , getAvarageUsagePerCPU( ) );
				try
				{
					Thread.sleep( refreshInterval );
				}
				catch ( InterruptedException e )
				{}
				for ( ThreadTime threadTime : values )
				{
					synchronized ( threadTime )
					{
						threadTime.setLast( threadTime.getCurrent( ) );
					}
				}
			}
		}

		private void mapNewThreads( long[] allThreadIds )
		{
			for ( long id : allThreadIds )
			{
				synchronized ( threadTimeMap )
				{
					if ( ! threadTimeMap.containsKey( id ) )
					{
						threadTimeMap.put( id , new ThreadTime( id ) );
					}
				}
			}
		}

		private void removeDeadThreads( Set< Long > mappedIds , long[] allThreadIds )
		{
			outer : for ( long id1 : mappedIds )
			{
				for ( long id2 : allThreadIds )
				{
					if ( id1 == id2 )
					{
						continue outer;
					}
				}
				synchronized ( threadTimeMap )
				{
					threadTimeMap.remove( id1 );
				}
			}
		}

		public void stopMonitor( )
		{
			this.stopped = true;
		}

		public double getTotalUsage( )
		{
			Collection< ThreadTime > values;
			synchronized ( threadTimeMap )
			{
				values = new HashSet< ThreadTime >( threadTimeMap.values( ) );
			}
			double usage = 0D;
			for ( ThreadTime threadTime : values )
			{
				synchronized ( threadTime )
				{
					usage += ( threadTime.getCurrent( ) - threadTime.getLast( ) ) / refreshInterval;
				}
			}
			usage = usage * 0.0001D;
			return usage;
		}
			
		public double getAvarageUsagePerCPU( )
		{
			return getTotalUsage( ) / opBean.getAvailableProcessors( );
		}
			
		public double getUsageByThread( Thread t )
		{
			ThreadTime info;
			synchronized ( threadTimeMap )
			{
				info = threadTimeMap.get( t.getId( ) );
			}
			double usage = 0D;
			if ( info != null )
			{
				synchronized ( info )
				{
					usage = ( info.getCurrent( ) - info.getLast( ) ) / ( refreshInterval * 10000 );
				}
			}
			return usage;
		}
	}

	private static class ThreadTime
	{
		private long id;
		private long last;
		private long current;
		
		public ThreadTime( long id )
		{
			this.id = id;
		}
		
		public long getId( )
		{
			return id;
		}
		
		public long getLast( )
		{
			return last;
		}
		
		public void setLast( long last )
		{
			this.last = last;
		}
		
		public long getCurrent( )
		{
			return current;
		}
		
		public void setCurrent( long current )
		{
			this.current = current;
		}
	}
		
	private final static Logger LOGGER = LoggerFactory.getLogger( JvmCpuUseage.class );
}
 *
 */
public class OperatingSystemMXBean02TestMain
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				File root = new File("D:/");
				long totalSize = root.getTotalSpace();
				long usableSize = root.getUsableSpace();
				long usedSize = totalSize - usableSize;
				
				if (flag) System.out.println("T:" + totalSize + ", A:" + usableSize + ", U:" + usedSize);
				if (flag) System.out.println(String.format("T:%,d, A:%,d, U:%,d", totalSize, usableSize, usedSize));
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		
		if (flag) {
			try {
				OperatingSystemMXBean opBean = ManagementFactory.getOperatingSystemMXBean();
				RuntimeMXBean runBean = ManagementFactory.getRuntimeMXBean();
				
				double beforeProcessTime = opBean.getSystemLoadAverage();
				long beforeUptime = runBean.getUptime();
				if (flag) System.out.println("BEFORE PT:" + beforeProcessTime + ", UT:" + beforeUptime);
				
				@SuppressWarnings("unused")
				long nCpus = opBean.getAvailableProcessors();
				for (int i=0; i < 100000000; i++) {
					nCpus = opBean.getAvailableProcessors();
				}
				
				double afterProcessTime = opBean.getSystemLoadAverage();
				long afterUptime = runBean.getUptime();
				if (flag) System.out.println("AFTER PT:" + afterProcessTime + ", UT:" + afterUptime);
				
				float cal = (float) ((afterProcessTime - beforeProcessTime) / ((afterUptime - beforeUptime) * 10000f));
				float usage = Math.min(99f, cal);
				
				
				if (flag) System.out.println("CAL:" + cal + ", USAGE:" + usage);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (flag) test01(args);
	}
}
