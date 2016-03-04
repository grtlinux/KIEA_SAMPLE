public class HowManyDaysMain {

	public static void main(String[] args) {
		HowManyDays how=new HowManyDays();
		System.out.println(how.howManyDays());
		how.setFrom(HowManyDays.makeDay(1975,8,28));
		how.setTo(HowManyDays.makeDay(2003,12,5));
		System.out.println(how.howManyDays());
		System.out.println(how.howManyDays()*24);
		System.out.println(how.howManyDays()*24*60);
	}
}
