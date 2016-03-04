public class LottoAnalysisMain {

	public static void main(String[] args) {
		LottoAnalysis lan=new LottoAnalysis();
		String[] lottos=lan.getLottos(LottoData.data[0]);
		System.out.println();
		for(int i=0;i<lottos.length;i++){
			System.out.print(lottos[i]+" ");
		}
		System.out.println();
		//System.out.println(09+"");// 에러 8진법 
		//09와 같이 0으로 시작하는 것을 10법 숫자로 변환
		System.out.println(lan.to10("09"));//
		
		int a[]=lan.toInt(LottoData.data[0]);
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
	}
}
