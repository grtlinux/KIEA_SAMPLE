public class Combination52 {

	public static void main(String[] args) {
		
		int count=0;
		for(int i=1;i<6;i++){
			for(int j=1;j<6;j++){
				for(int k=1;k<6;k++){
					if((i<j) && (j<k) && (i<k)){
						count++;
						System.out.println(i+"  "+j+"  "+k);
					}
				}//for
			}//for
		}//for
		
		System.out.println("5C3= "+count); 
		
		int combiCount=getCombiCount(5);
		System.out.println("5C3= "+combiCount); 
		
		printCombi(6);
	}
	
	public static int getCombiCount(int m){
		int count=0;
		for(int i=1;i<=m;i++){
			for(int j=1;j<=m;j++){
				for(int k=1;k<=m;k++){
					if((i<j) && (j<k) && (i<k)){
						count++;
					}
				}//for
			}//for
		}//for
		return count;
	}
	public static void printCombi(int m){
		for(int i=1;i<=m;i++){
			for(int j=1;j<=m;j++){
				for(int k=1;k<=m;k++){
					if((i<j) && (j<k) && (i<k)){
						System.out.println("["+i+","+j+","+k+"]");
						//System.out.printf("[%d,%d,%d%n]",i,j,k);
					}
				}//for
			}//for
		}//for
		System.out.println(m+"C3= "+getCombiCount(m));
	}
}
