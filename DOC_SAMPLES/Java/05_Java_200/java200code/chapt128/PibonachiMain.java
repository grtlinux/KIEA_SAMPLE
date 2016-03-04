public class PibonachiMain {
	
	public static void main(String[] args) {
		Pibonachi pibo=new Pibonachi();
		for(int i=1;i<36;i++){
			double la=System.currentTimeMillis();
			int aa=pibo.pibo(i);
			double ab=pibo.golden(i) ;
			double lb=System.currentTimeMillis();
			System.out.println(ab+"  "+aa+" : "+(lb-la));
		}
	}
}
