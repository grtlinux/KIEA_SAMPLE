import java.util.Arrays;
public class ArrayUsinAPIMain {

	public static void main(String[] args) {
		
		int [] a={5,4,7,1,9,12,0,3,2,6};
		int [] b=new int[a.length];
		int [] c={5,4,7,1,9,12,0,3,2,6};
		int [] d=null;
		//Made by user
		ArrayNotUsinAPI.arrarycopy(a,b);
		System.out.println(ArrayNotUsinAPI.equals(a,b));
		ArrayNotUsinAPI.fill(b,-6);
		ArrayNotUsinAPI.sort(a);
		ArrayNotUsinAPI.prints(a);//배열 a 출력
		//API
		System.arraycopy(a,0,b,0,a.length);
		d=(int [])c.clone();
		System.out.println(Arrays.equals(d,c));
		Arrays.fill(a,-5);
		Arrays.sort(c);
		ArrayNotUsinAPI.prints(c);//배열 a 출력
	}
}
