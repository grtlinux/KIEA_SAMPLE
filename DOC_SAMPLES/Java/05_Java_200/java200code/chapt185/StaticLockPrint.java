public class  StaticLockPrint implements Runnable{
	private static int i; //
	static { i=5;}
	public void run(){show();}
	public void show(){
		synchronized(StaticLockPrint.class){
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
}
/*
synchronized(StaticLockPrint.class){
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
*/
