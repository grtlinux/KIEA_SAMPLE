public class  MyRun implements Runnable
{
	public void run(){
		show();
	}
	public void show(){
		for(int i=0;i<100;i++){
			System.out.print("S");
		}
	}
}
