public class LottoMain2 {

	public static void main(String[] args) {
		Lotto lotto =new Lotto();
		BubbleSort bubble=new BubbleSort();
		lotto.make();
		BubbleSort.print(lotto.getBall());
		int [] b=lotto.getBall();
		java.util.Arrays.sort(b);
		BubbleSort.print(b);//·Î¶Ç
	}
}
