public class ReverseArrayMain {

	public static void main(String[] args) {
		int [] a={5,6,3,7,4,8,2,9};
		ReverseArray ra=new ReverseArray();
		BubbleSort bsort=new BubbleSort();
		BubbleSort.print(a);//a출력
		int [] c=bsort.bbsortinc(a);
		int [] b=ra.reverse(a);
		BubbleSort.print(c);//a bubble 출력
		BubbleSort.print(b);//b reverse
		ra.reverseb(c);//c reverse 
		BubbleSort.print(c);
	}
}
