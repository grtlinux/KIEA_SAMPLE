import java.util.Arrays;
/**
 * Created on 2003. 10. 6.
 */
public class Sorts {
	
	public static int[] heapAPI1(int [] a){
		int[] b=a;
		Arrays.sort(b);
		return b;
	}
	public static void heapAPI2(int [] a){
		
		Arrays.sort(a);
		
	}
	public static int[] bbsort(int [] a){
		int n=a.length;
		//System.out.println("1  bb : "+ n);
		if(n<=0){
			return new int[0];
		}
		//System.out.println("2  bb : "+ n);
		for(int i=0;i<n-1;i++){
			//System.out.println(i+"  ==>  : ");
			for(int j=0;j<n-1-i;j++){
			
				if(a[j]>a[j+1]){  
					int temp=a[j+1];  //스왑
					a[j+1]=a[j];
					a[j]=temp;
					prints(a);  // 현재 값을 출력 이것을 코멘트하면 값만 넘겨짐
				}
			}
		}
		return a;
	}
    //int 배열을 출력
	public static void prints(int [] a){
	int count=a.length;
	System.out.print("[ ");
		for(int i=0;i<count-1;i++){
			System.out.print(a[i]+" , ");
		}
	System.out.println(a[count-1]+" ]");
	}
	
    private static void quick(int [] nums,int start,int end){
  	int left=start;
  	int right=end;
  	int mid=nums[(start+end)/2];
  	int temp=0;
  	do{
  		while((nums[left]<mid) && (left<end)){
  			left++;
  		}
  		while((mid<nums[right]) && (right>start)){
			right--;
  		}
  		if(left<=right){
  			temp=nums[left];
			nums[left]=nums[right];
			nums[right]=temp;
			left++;
			right--;
  		}
		
  	}while(left<right);
	//
	prints(nums);
  	if(start<right){
		quick(nums,start,right);
  	}
	if(left<end){
		quick(nums,left,end);
	}
  }
  
  public static int [] quickSort(int [] a){
  	int [] b=a;
	quick(b,0,b.length-1);
	return b;
  }
	
  public static void insertSort(int [] a){
  	for(int i=0;i<a.length;i++){
  		int temp=a[i];
  		int j=i-1;
  		while(j>=0&& a[j]>temp){
  			a[j+1]=a[j]; 
  			j--;
  		}
  		a[j+1]=temp;
		prints(a);
  	}
  }	
}
