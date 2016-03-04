public class OtherMathMain {

	public static void main(String[] args) {
		
		System.out.println(OtherMath.sub(4.6,5.7));
		
		OtherMath other=new OtherMath(4.6,5.7);
		System.out.println(other.sum());
		System.out.println(other.multi());
		other.setA(4);
		other.setB(5);
		System.out.println(other.sum());
		System.out.println(other.multi());
	}
}
