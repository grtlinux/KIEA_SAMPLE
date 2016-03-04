public class GuGuDan48 {

	public static void main(String[] args) {
		printGuGu();
		System.out.println("-----------------------------");

		GuGu gu=new GuGu();
		gu.printGu();
		System.out.println("-----------------------------");

		GuGu.printGuGu();
	}
	public static void printGuGu(){
		for(int i=1; i<=9;i++){
			for(int j=1; j<=9;j++){
				System.out.printf("%d*%d=%d\t",j,i,i*j);
			}
			System.out.println();
		}
	}
}

class GuGu
{
	public void printGu(){
		for(int i=1; i<=9;i++){
			for(int j=1; j<=9;j++){
				System.out.printf("%d*%d=%d\t",j,i,i*j);
			}
			System.out.println();
		}
	}
	public static void printGuGu(){
		for(int i=1; i<=9;i++){
			for(int j=1; j<=9;j++){
				System.out.printf("%d*%d=%d\t",j,i,i*j);
			}
			System.out.println();
		}
	}
}
