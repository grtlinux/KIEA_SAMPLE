public class ArrayTwoOneMain {

	public static void main(String[] args) {
		int[][] lotto={{2,7,19,21,26,31},{6,24,35,37,38,41},
				   {18,20,26,31,37,41},{2,7,8,17,26,40}};
		ArrayTwoOne ato=new ArrayTwoOne();
		int [] array1=ato.twoToOne(lotto);
		ArrayPrint.print(lotto);
		ArrayPrint.print(array1);
	}
}
