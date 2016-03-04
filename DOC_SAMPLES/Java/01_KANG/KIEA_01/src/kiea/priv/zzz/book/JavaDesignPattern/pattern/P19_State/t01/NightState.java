package kiea.priv.zzz.book.JavaDesignPattern.pattern.P19_State.t01;

public class NightState implements State
{
	private NightState() {}
	
	private static NightState instance = new NightState();
	
	public static State getInstance()
	{
		return instance;
	}
	
	public void doClock(Context context, int hour)
	{
		if (9 <= hour && hour < 17) {
			context.changeState(DayState.getInstance());
		}
	}
	
	public void doUse(Context context)
	{
		context.callSecurityCenter("비상:야간의 금고사용");
	}
	
	public void doAlarm(Context context)
	{
		context.callSecurityCenter("비상벨(야간)");
	}
	
	public void doPhone(Context context)
	{
		context.recordLog("야간의 통화녹음");
	}
	
	public String toString()
	{
		return "[야간] ";
	}
}
