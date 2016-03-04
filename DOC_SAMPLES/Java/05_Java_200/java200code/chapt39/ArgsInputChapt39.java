public class ArgsInputChapt39 {

	public static void main(String[] args) {
		if(args.length<=0){
			System.out.println("아규먼트가 없다.");
		}else if(args.length==1){
			System.out.println("아규먼트 1개: "+args[0]);
		}else if(args.length==2){
			System.out.printf("아규먼트 2개: %s %s",args[0],args[1]);
		}else{
			System.out.printf("Too many!!");
		}
	}
}
