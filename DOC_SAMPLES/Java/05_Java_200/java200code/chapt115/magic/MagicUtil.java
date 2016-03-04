public class MagicUtil 
{
	public static void magicPrint(int [][] magic){
		int count=magic.length;
		for(int i=0;i<count;i++){
			for(int j=0;j<count;j++){
				System.out.print("("+i+","+j+")="+magic[i][j]+"  ");
			}
			System.out.println();
		}
	}//
	public static void magicPrint(int [] magic){
		int count=magic.length;
		for(int j=0;j<count;j++){
			System.out.print("("+j+")="+magic[j]+"  ");
		}
		System.out.println();
	}//

	public static int[] magicSum(int [][] magic){
		int count=magic.length;
		int []total=new int[2*count+2];
		
		for(int k=0;k<count;k++){
			for(int i=0;i<count;i++){
				for(int j=0;j<count;j++){
					if(k==i){total[i]+=magic[i][j];}
					if(k==j){total[count+j]+=magic[i][j];}
					if(k==0 && i==j){total[2*count]+=magic[i][j];}
					if(k==0 && i+j+1==count){total[2*count+1]+=magic[i][j];}
				}
			}
		}
		return total;
	}//
	public static boolean  isMagicSuccess(int [] magic){
		boolean isS=true;
		int count=magic.length;
		for(int j=0;j<count-1;j++){
			if(magic[j]==magic[j+1]){
				isS &= true;
			}else{
				isS &= false;
				break;
			}
		}
		return isS;
	}//
}
