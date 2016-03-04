public class  BubbleSortMain
{
	public static void main(String[] args) 
	{
		int [] a={5,6,3,7,4,8,2,9};
		BubbleSort.print(a);
		BubbleSort bubble=new BubbleSort();
		int [] b=bubble.bbsortinc(a);
		BubbleSort.print(b);
	}
}
