public class LottoMain {

	public static void main(String[] args) {
		Lotto lotto =new Lotto();
		BubbleSort bubble=new BubbleSort();
		lotto.make();
		BubbleSort.print(lotto.getBall());
		int [] b=bubble.bbsortinc(lotto.getBall());
		BubbleSort.print(b);//·Î¶Ç
	}
}
