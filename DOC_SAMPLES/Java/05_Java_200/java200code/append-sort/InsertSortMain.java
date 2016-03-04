/**
 * Created on 2003. 10. 6.
 */
public class InsertSortMain {

	public static void main(String[] args) {
		int [] a={4,7,2,12,9,11,3,65};
		Sorts.prints(a);
		Sorts.insertSort(a);
		Sorts.prints(a);
	}
}
