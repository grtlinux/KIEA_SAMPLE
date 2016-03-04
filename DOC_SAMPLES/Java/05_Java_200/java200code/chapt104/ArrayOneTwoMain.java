public class ArrayOneTwoMain {

	public static void main(String[] args) {
		
		int [] arrays1={4,5,6,3,4,5,6,7,8,2,5,14};//12
		ArrayOneTwo aot=new ArrayOneTwo();
		aot.setArray(arrays1);
		int [][] arrays2=aot.oneToTwo(3,4);//3X4·Î 
		ArrayPrint.print(arrays1);
		ArrayPrint.print(arrays2);
	}
}
