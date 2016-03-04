public class Tank {
	
	public static int howmany=0;
	public int howfast=100;
	
	public void input(){
		howmany++;
	}
	public void increase(){
		howfast+=30;
	}
	public static int getHowMany(){
		return howmany;
	}
	public int getHowFast(){
		return howfast;
	}
	public void showShortLife(){
		int age=5;
		howfast-=age;
	}
}
