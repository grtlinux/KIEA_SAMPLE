public class LottoSameThreeMain {

	public static void main(String[] args) {
		LottoSameThree lotto=new LottoSameThree(12);
		lotto.make();
		BubbleSort.print(lotto.getBall());
		lotto.setSame(18);
		lotto.make();
		BubbleSort.print(lotto.getBall());
	}
}

