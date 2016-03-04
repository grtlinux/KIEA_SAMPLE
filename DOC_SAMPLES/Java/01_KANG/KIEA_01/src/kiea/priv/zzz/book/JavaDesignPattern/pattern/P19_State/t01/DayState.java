package kiea.priv.zzz.book.JavaDesignPattern.pattern.P19_State.t01;

public class DayState implements State
{
	private DayState() {}
	
	private static DayState instance = new DayState();
	
	public static State getInstance()
	{
		return instance;
	}
	
	public void doClock(Context context, int hour)
	{
		if (hour < 9 || 17 <= hour) {
			context.changeState(NightState.getInstance());
		}
	}
	
	public void doUse(Context context)
	{
		context.recordLog("금고사용(주간)");
	}
	
	public void doAlarm(Context context)
	{
		context.callSecurityCenter("비상벨(주간)");
	}
	
	public void doPhone(Context context)
	{
		context.callSecurityCenter("일반동화(주간)");
	}
	
	public String toString()
	{
		return "[주간] ";
	}
}
