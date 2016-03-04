/**
 * Created on 2003. 10. 6.
 */
public class QuickSortMain {

	public static void main(String[] args) {
		//int [] a={6,4,8,7,9,5};
		int [] a={4,7,2,12,9,11,3,65};
		Sorts.prints(a);
		Sorts.quickSort(a);
		Sorts.prints(a);
	}
}
