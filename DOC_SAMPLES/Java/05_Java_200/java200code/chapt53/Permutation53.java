public class Permutation53 {

	public static void main(String[] args) {

		int permuCount=getPermuteCount(5);
		System.out.println("5P3= "+permuCount); 
		
		printPermute(5);
	}
	
	public static int getPermuteCount(int m){
		int count=0;
		for(int i=1;i<=m;i++){
			for(int j=1;j<=m;j++){
				for(int k=1;k<=m;k++){
					if((i!=j) && (j!=k) && (i!=k)){
						count++;
					}
				}//for
			}//for
		}//for
		return count;
	}
	public static void printPermute(int m){
		int iters=0;
		for(int i=1;i<=m;i++){
			for(int j=1;j<=m;j++){
				for(int k=1;k<=m;k++){
					if((i!=j) && (j!=k) && (i!=k)){
						
						System.out.print("["+i+","+j+","+k+"] ");
						//System.out.printf("[%d,%d,%d%n]",i,j,k);
						if((++iters%5)==0){
							System.out.println();
						}
					}
				}//for
			}//for
		}//for
	}
}
