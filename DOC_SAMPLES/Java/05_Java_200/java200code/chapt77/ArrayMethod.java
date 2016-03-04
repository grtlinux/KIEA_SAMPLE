public class ArrayMethod {

	public static void copyV(int [] q,int [] p){
		for(int i=0;i<q.length;i++){
			p[i]=q[i];
		}
	}//System.arraycopy
	public static void bbsort(int [] a){   
		int n=a.length;
		for(int i=0;i<n-1;i++){
		   for(int j=0;j<n-1-i;j++){
			  if(a[j]>a[j+1]){  
				int temp=a[j+1];  //스왑
				a[j+1]=a[j];
				a[j]=temp;
			  }
		   }
		}
	} 
	public static void copyR(int [] q,int [] p){
		p=q;
	}
	public static void copyRChange(int [] q,int [] p){
		p=q;
		p[0]=35;
	}
	public static void copyVInt(int q,int p){
		p=q;
	}
	public static void multi(int q){
		q=q*10;
	}
	public static void print(int [] p){
		for(int i=0;i<p.length;i++){      //값들이 변한다.
			System.out.print("["+p[i]+"] ");  //레퍼런스 타입은 
		}
		System.out.println();
	}
}
