public class LottoCountingMain {

	public static void main(String[] args) {
		LottoAnalysis lan=new LottoAnalysis();
		/*
		int[][] lotto={{2,7,19,21,26,31},{6,24,35,37,38,41},
				       {3,8,14,20,31,32},{1,5,6,10,17,27},
					   {2,9,11,15,21,35},{5,20,25,26,35,45},
					   {3,7,8,11,14,40},{1,4,9,17,31,37},
					   {18,20,26,31,37,41},{2,7,8,17,26,40}};
					   */
		int[][] lotto={{2,6,20,26,27,40},{2,17,18,19,26,45},
			       {26,29,35,38,39,44},{11,20,27,40,43,45},
				   {5,11,16,25,29,39}};
		//int []realLotto={1,4,5,6,9,31};
		int []realLotto=lan.toInt(LottoData.data[0]);
		LottoCounting lottoc=new LottoCounting(realLotto);
		lottoc.make(lotto);
		lottoc.printLotto();
	}
}
