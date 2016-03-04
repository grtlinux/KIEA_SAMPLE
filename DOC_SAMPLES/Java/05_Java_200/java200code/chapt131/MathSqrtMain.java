public class MathSqrtMain {

	public static void main(String[] args) {
		MathSqrt ms=new MathSqrt(100,0);
		System.out.println("SQRT(100) :"+ms.fuctValue());
		
		ms.setA(3.0);    //구하고자 하는 Root값 SQRT(3) 양의 3제곱근
		ms.setXMin(2);   //추측값
	    System.out.println("SQRT(3) :"+ms.fuctValue());
	    
		ms.setA(11.0);    //구하고자 하는 Root값 SQRT(11) 양의 11제곱근
		ms.setXMin(2);   //추측값
		System.out.println("SQRT(11) :"+ms.fuctValue());
		ms.setA(3.0);    //구하고자 하는 Root값 SQRT(3) 양의 3제곱근
		ms.setXMin(-1);   //추측값
		System.out.println("SQRT(3) :"+ms.fuctValue());
		System.out.println("SQRT(3) :"+ms.fuctValue());
		ms.setA(8.0);    //구하고자 하는 Root값 SQRT(8) 양의 8제곱근
		ms.setXMin(2);   //추측값
		System.out.println("SQRT(8) :"+ms.fuctValue());
		System.out.println("SQRT(8) :"+Math.sqrt(8));
		System.out.println("실제 값과 차이 :"+(Math.sqrt(8)-ms.fuctValue()));
	}
}
