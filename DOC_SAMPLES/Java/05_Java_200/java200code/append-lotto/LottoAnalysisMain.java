
public class LottoAnalysisMain {

	public static void main(String[] args) {
		LottoAnalysis lan=new LottoAnalysis();
		//1회차 10  23  29  33  37  40 16 2002.12.07 2003.03.10
		//에서 로또 6개와 보너스 1개만 가져온다. 
		System.out.println(lan.getLottos(lan.lottos[0]));
		
		//System.out.println(09+"");// 에러 8진법 
		//09와 같이 0으로 시작하는 것을 10법 숫자로 변환
		System.out.println(lan.to10("09"));
		
		int a[]=lan.toInt(lan.getLottos(lan.lottos[0]));
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
	}
}
