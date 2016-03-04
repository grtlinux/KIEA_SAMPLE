public class LottoSameTwoMain {

	public static void main(String[] args) {
		LottoSameTwo lotto=new LottoSameTwo(10);
		lotto.make();
		BubbleSort.print(lotto.getBall());
		lotto.setSame(12);
		lotto.make();
		BubbleSort.print(lotto.getBall());
	}
}
