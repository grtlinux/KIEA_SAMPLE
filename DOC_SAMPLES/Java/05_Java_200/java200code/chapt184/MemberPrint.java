public class  MemberPrint implements Runnable{
	private  int i=0; //
	public void run(){show();}
	public void show(){
		for(  ;i<100;i++){
			if(((Thread.currentThread()).getName()).equals("a") ){
				System.out.print("[A"+i+"]");
			}else if(((Thread.currentThread()).getName()).equals("b") ){
				System.out.print("[B"+i+"]");
			}else if(((Thread.currentThread()).getName()).equals("c") ){
				System.out.print("[C"+i+"]");
			}
		}
	}
}
