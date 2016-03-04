
public class Complex2Main {

	public static void main(String[] args) {
		Complex2 z1=new Complex2(3,5);
		Complex2 z2=new Complex2(3,5);
		Complex2 z3=new Complex2(3,4);
		System.out.println("z1 = "+z1);
		System.out.println("z2 = "+z2);
		if(z1.equals(z2)){
			System.out.println(z1+"=="+z2);
		}else{
			System.out.println(z1+"!="+z2);//같은데!!!!!!
		}  
		System.out.println("z1.hashCode() = "+z1.hashCode());
		System.out.println("z3.hashCode() = "+z2.hashCode());
		if(z1.equals(z3)){
			System.out.println(z1+"=="+z3);
		}else{
			System.out.println(z1+"!="+z3);//같은데!!!!!!
		}  
		System.out.println("z1.hashCode() = "+z1.hashCode());
		System.out.println("z3.hashCode() = "+z3.hashCode());
		
	}
}
