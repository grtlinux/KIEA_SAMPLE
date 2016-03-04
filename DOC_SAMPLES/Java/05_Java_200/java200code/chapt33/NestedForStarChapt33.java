public class NestedForStarChapt33 {

	public static void main(String[] args) {
		//ÁßÃ¸for
		for(int i=0;i<10;i++){
			for(int j=0;j<=i;j++){
				System.out.print("#");
			}
			System.out.println();
		}
		for(int i=0;i<11;i++){
			for(int j=0;j<=10-i;j++){
				System.out.print("@");
			}
			System.out.println();
		}
	}
}
