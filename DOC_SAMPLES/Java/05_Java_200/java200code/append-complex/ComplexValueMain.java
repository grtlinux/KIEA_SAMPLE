
public class ComplexValueMain {

	public static void main(String[] args) {
		double t1=ComplexValue.firstTheta(new Complex(1,1));//라디안기본
		t1=Math.toDegrees(t1);
		System.out.println(t1);
		//ComplexValue
		double t2=ComplexValue.size(3,new Complex(1,1)); //z^3=1+1i
		System.out.println(t2);
		
		//근 구하기 
		Complex[] comp=ComplexValue.compRoot(3,new Complex(1,1));
		//z^3=1+1 i의 값은 
		for(int i=0;i<comp.length;i++){
			System.out.println(comp[i]);
		}
		///각 구하기 
		
		double[] thetas=ComplexValue.thetas(3,new Complex(1,1));
		//z^3=1+1 i의 값은 
		for(int i=0;i<thetas.length;i++){
			System.out.println(Math.toDegrees(thetas[i])+" degree");
		}
	}
}
