
public class Complex1Main {

	public static void main(String[] args) {
		Complex1 z1=new Complex1(3,5);
		Complex1 z2=new Complex1(3,5);
		System.out.println("z1 = "+z1);
		System.out.println("z2 = "+z2);
		if(z1.equals(z2)){
			System.out.println(z1+"=="+z2);
		}else{
			System.out.println(z1+"!="+z2);//°°Àºµ¥!!!!!!
		}  
		System.out.println("z1.hashCode() = "+z1.hashCode());
		System.out.println("z2.hashCode() = "+z2.hashCode());
		
	}
}
