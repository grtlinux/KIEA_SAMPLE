public class  BubbleSort
{
	public  int[] bbsortinc(int [] a){
		int n=a.length;
		if(n<=0){
			return new int[0];
		}
		for(int i=0;i<n-1;i++){
			for(int j=0;j<n-1-i;j++){
				if(a[j]>a[j+1]){  
					int temp=a[j+1];  //스왑
					a[j+1]=a[j];
					a[j]=temp;
				}
			}
		}
		return a;
	}
	public static void print(int [] a){
		int count=a.length;
		System.out.print("["+a[0]+", ");
		for(int i=1;i<count-1;i++){
			System.out.print(a[i]+", ");
		}
		System.out.println(a[count-1]+"]");
	}
}
/*
public  int[] bbsortdec(int [] a){
	int n=a.length;
	if(n<=0){
		return new int[0];
	}
	for(int i=0;i<n-1;i++){
		for(int j=0;j<n-1-i;j++){
			if(a[j]<a[j+1]){  
				int temp=a[j+1];  //스왑
				a[j+1]=a[j];
				a[j]=temp;
			}
		}
	}
	return a;
}
*/
