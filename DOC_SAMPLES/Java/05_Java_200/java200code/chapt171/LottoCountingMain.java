public class LottoCountingMain {

	public static void main(String[] args) {
		LottoAnalysis lan=new LottoAnalysis();
		LottoCounting lottoc=new LottoCounting();

		int[][] lotto={{2,6,20,26,27,40},{2,17,18,19,26,45},
			       {26,29,35,38,39,44},{11,20,27,40,43,45},
				   {5,11,16,25,29,39}};//내가 선택한 로또

		//int []realLotto={1,4,5,6,9,31};
		int [] realLotto=lan.toInt(LottoData.data[0]);//
		lottoc.setLotto(realLotto);
		lottoc.make(lotto);
		lottoc.printLotto();
	}
}
