public class SystemTestJava5 {

	public static void main(String[] args) {
		long namotime=System.nanoTime();
		for(int i=0;i<10000;i++){
			System.out.print("");
		}
		long namotime2=System.nanoTime();//10^-9 nano java5
		System.out.println((namotime2-namotime));//1/1000000000초			
		//java2에서는 deprecate되었다. java5 사용하자.
		System.out.println(System.getenv("JAVA_HOME")); //java 5
		//모든 env구하기
		java.util.Map map=System.getenv();  //java5는 사용하자.
		java.util.Iterator iter= map.keySet().iterator();
		int j=0;
		while(iter.hasNext()){
			String keys=(String)iter.next();
			System.out.println((++j+" ")+keys+" : "+map.get(keys));
		}
	}//main
}

