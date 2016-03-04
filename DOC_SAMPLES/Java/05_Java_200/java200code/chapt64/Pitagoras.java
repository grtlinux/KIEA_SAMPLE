public class Pitagoras {

	public static void main(String[] args) {
		//x^2+y^2=z^2을 만족시키는 정수 x,y,z를 찾자. 
		for(int z=1;z<100;z++){
			for(int y=1;y<100;y++){
				for(int x=1;x<100;x++){
					//z>y>x의 수 중에서 피타고라스 정리를 만족시키는 수
					if(z>x && y>x && z>y && z*z==x*x+y*y){
						System.out.println("피타고라스 수 : x="+x+" y="+y+" z="+z);
					}
				}
			}
		}
		printPita(100);
	}//main
	public static boolean isPita(int x,int y,int z){
		boolean isP=false;
		if(x*x==y*y+z*z){
			isP=true;
		}
		return isP;
	}//
	public static boolean isLarge(int x,int y,int z){
		boolean isP=false;
		if(z>x && y>x && z>y){
			isP=true;
		}
		return isP;
	}//
	public static void printPita(int n){
		for(int z=1;z<n;z++){
			for(int y=1;y<n;y++){
				for(int x=1;x<n;x++){
					//z>y>x의 수 중에서 피타고라스 정리를 만족시키는 수
					if(isLarge(x,y,z)&& isPita(z,y,x)){
						System.out.println("피타고라스 수 : x="+x+" y="+y+" z="+z);
					}
				}
			}
		}
	}
}
