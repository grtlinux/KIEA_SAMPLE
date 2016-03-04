public class IfTest1Chapt32 {

	public static void main(String[] args) 
	{
		int valuA=90;
		int valuB=80;
		int valuC=70;
		int valuD=60;

		int a=78;
		if(a>=valuA){
			System.out.println("A");
		}else if(a>=valuB){
			System.out.println("B");
		}else if(a>=valuC){
			System.out.println("C");
		}else if(a>=valuD){
			System.out.println("D");
		}else{
			System.out.println("F");
		}
		int b=78;
		if(b>=valuA){
			System.out.println("A");
		}else{
			if(b>=valuB){
				System.out.println("B");
			}else{
				if(b>=valuC){
					System.out.println("C");
				}else{
					if(b>=valuD){
						System.out.println("D");
					}else{
						System.out.println("F");
					}
				}
			}
		}
	}//main
}
