package kiea.priv.zzz.etc.stopwatch.t01;

class StopWatch
{
	private long time1;
	
	private StopWatch()
	{
		reset();
	}
	
	public long init()
	{
		return reset();
	}
	
	public long reset()
	{
		this.time1 = System.nanoTime();
		return this.time1;
	}

	public long getSeconds()
	{
		long diff = System.nanoTime() - this.time1;
		
		return diff / 1000 / 1000 / 1000;
	}
	
	public long getMilliSeconds()
	{
		long diff = System.nanoTime() - this.time1;
		
		return diff / 1000 / 1000;
	}
	
	public long getMicroSeconds()
	{
		long diff = System.nanoTime() - this.time1;
		
		return diff / 1000;
	}
	
	public long getNanoSeconds()
	{
		long diff = System.nanoTime() - this.time1;
		
		return diff;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static StopWatch instance = null;
	
	public static synchronized StopWatch getInstance()
	{
		if (StopWatch.instance == null) {
			StopWatch.instance = new StopWatch();
		}
		
		return StopWatch.instance;
	}
}

public class StopWatchTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static boolean flag = true;
	
	private static void test01(String[] args)
	{
		if (flag) {
			/*
			 * 객체생성 및 초기화
			 */
			StopWatch.getInstance();
			
			/*
			 * 처리-1
			 */
			try { Thread.sleep(1234); } catch (InterruptedException e) {}
			
			/*
			 * 중간측정
			 */
			System.out.println("1> " + StopWatch.getInstance().getSeconds() + " seconds");
			System.out.println("1> " + StopWatch.getInstance().getMilliSeconds() + " milli seconds");
			System.out.println("1> " + StopWatch.getInstance().getMicroSeconds() + " micro seconds");
			System.out.println("1> " + StopWatch.getInstance().getNanoSeconds() + " nano seconds");
			
			/*
			 * 처리-2
			 */
			try { Thread.sleep(2234); } catch (InterruptedException e) {}
			
			/*
			 * 중간측정
			 */
			System.out.println("2> " + StopWatch.getInstance().getSeconds() + " seconds");
			System.out.println("2> " + StopWatch.getInstance().getMilliSeconds() + " milli seconds");
			System.out.println("2> " + StopWatch.getInstance().getMicroSeconds() + " micro seconds");
			System.out.println("2> " + StopWatch.getInstance().getNanoSeconds() + " nano seconds");
			
			/*
			 * 재설정 초기화
			 */
			StopWatch.getInstance().reset();
			
			/*
			 * 처리
			 */
			try { Thread.sleep(1000); } catch (InterruptedException e) {}
			
			/*
			 * 중간측정
			 */
			System.out.println("3> " + StopWatch.getInstance().getSeconds() + " seconds");
			System.out.println("3> " + StopWatch.getInstance().getMilliSeconds() + " milli seconds");
			System.out.println("3> " + StopWatch.getInstance().getMicroSeconds() + " micro seconds");
			System.out.println("3> " + StopWatch.getInstance().getNanoSeconds() + " nano seconds");
		}
	}
	
	public static void main(String[] args)
	{
		if (flag) test01(args);
	}
}
