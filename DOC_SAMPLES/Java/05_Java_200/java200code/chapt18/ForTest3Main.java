public class  ForTest3Main
{
	public static void main(String[] args) 
	{
		
		for( int i=0; i<10;i++){
			if(i%2==0){
				continue;
			}
			System.out.print(" [ "+i+" ] ");
			//System.out.printf(" [ %d ] ",i);
		}
		System.out.println();
		int j=17;
		for(  ;   ; j+=2){
			System.out.print(" [ "+j+" ] ");
			//System.out.printf(" { %d  } ",j);
			if(j>30){ 
				break; 
			}
		}
		System.out.println("\nfor문 결과  :  "+j);
		//System.out.printf("%nfor문 결과  :  %d%n",j);
	}
}
