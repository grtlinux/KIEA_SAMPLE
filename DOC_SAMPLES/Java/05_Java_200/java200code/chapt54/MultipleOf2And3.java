public class MultipleOf2And3 {

	public static void main(String[] args) {
	    //2또는 3의 배수
		for(int j=1;j<20;j++){
		   if( j%3==0 || j%2==0 ){
			   System.out.print("["+j+"] ");
		   }
	    }
	    System.out.println();
	    //2의 배수가 아닌 3의 배수
	    for(int j=1;j<50;j++){
		   if( j%3==0 && j%2!=0 ){
			   System.out.print("["+j+"] ");
		   }
	    }
	    System.out.println();
	    
	    printMultiple(20);
	    printMulAnNotB(50,3,2);
	    
	    printMulAnNotB(50,2,3);
	}
	
	public static void printMultiple(int m){
		if(m<1){
			System.out.println("1이상의 양수를 입력하시오");
			return ;
		}
		for(int j=1;j<m;j++){
		   if( j%3==0 || j%2==0 ){
			   System.out.print("["+j+"] ");
		   }
	    }
	    System.out.println();
	}
	public static void printMulAnNotB(int n,int jj,int kj){
		if(n<1 || jj<=1 || kj<=1 || jj==kj){
			System.out.println("알맞은 수를 입력하시오");
			return ;
		}
		for(int j=1;j<n;j++){
		   if( j%jj==0 && j%kj!=0 ){
			   System.out.print("["+j+"] ");
		   }
	    }
	    System.out.println();
	}
}
