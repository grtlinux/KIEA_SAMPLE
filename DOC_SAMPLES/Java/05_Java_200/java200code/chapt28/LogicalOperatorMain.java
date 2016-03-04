public class LogicalOperatorMain {

	public static void main(String[] args) {
		  //1의 자리가 3이나 6이나 9인 것.
		  for(int i=0; i<50; i++){
			  if(((i%10)%3==0) && (i%10!=0)){
				  System.out.print("["+i+"] ");
			  }
		  }
		  System.out.println();
		  //1의 자리가 3, 6, 9가 아닌 것.
		  for(int i=0; i<50; i++){
			  if(!(((i%10)%3==0) && (i%10!=0))){
				  System.out.print("["+i+"] ");
			  }
			  if((i+1)%10==0){
				  System.out.println();
			  }
		  }
		  System.out.println();
	
		  for(int i=0; i<50; i++){
			  if(((i%10)%3!=0) || (i%10==0)){
				  System.out.print("["+i+"] ");
			  }
			  if((i+1)%10==0){
				  System.out.println();
			  }
		  }
		  
		  System.out.println();
		  //3의 배수 중 2의 배수 제외
		  for(int j=1, k=30; (k>=10 && j<=30);j++, k--){
			  if( j%3==0 && j%2!=0 ){
				  System.out.print("["+k+","+j+"] ");
				  //System.out.printf("[%d,%d] ",k,j);
			  }
		  }
		  System.out.println();
		  
		  for(int j=1, k=30; (k>=10 && j<=30);j++, k--){
			  if( j%3==0 && j%2!=0 ){
				  System.out.print("["+k+","+j+"] ");
				  //System.out.printf("[%d,%d] ",k,j);
			  }
		  }
		  System.out.println();
	}
}
