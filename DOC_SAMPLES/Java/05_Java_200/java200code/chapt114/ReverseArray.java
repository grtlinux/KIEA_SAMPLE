public class ReverseArray {
	public int[] reverse(int [] arr){
		int count=arr.length;
		int[] a=new int[count];
		for(int i=0;i<count;i++){
			a[count-i-1]=arr[i];
		}
		return a;
	}
	public void reverseb(int [] arr){
		int count=arr.length;
		for(int i=0;i<=count/2;i++){
			int temp=arr[i];
			arr[i]=arr[count-i-1];
			arr[count-i-1]=temp;
		}
	}
}
