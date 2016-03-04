
public class ArrayMethodMain {

	public static void main(String[] args) {

		int a=5;
		int b=6;
		ArrayMethod.copyVInt(a,b);//call by value
		System.out.println(a); //변경되지 않느다.
		System.out.println(b); //변경되지 않느다.
		ArrayMethod.multi(a);  //call by value
		System.out.println(a);//변경되지 않느다.
		
		int [] aa={5,4,7,1,9,12,0,3,2,6};
		int [] bb=new int[aa.length];
		int [] cc={5,4,7,1,9,12,0,3,2,6};
		int [] dd=new int[cc.length];
		
		ArrayMethod.copyR(aa,bb);  //call by reference로 copy
		ArrayMethod.print(bb);
		ArrayMethod.copyRChange(aa,bb);
		ArrayMethod.print(bb);
		ArrayMethod.print(aa);
		
		ArrayMethod.bbsort(aa);  //call by reference로 sorting
		ArrayMethod.print(aa);  
		
		ArrayMethod.copyV(cc,dd);  //dd의 값들은 모두 0이었다. 
		ArrayMethod.print(dd); //call by value로 copy
	}
}
