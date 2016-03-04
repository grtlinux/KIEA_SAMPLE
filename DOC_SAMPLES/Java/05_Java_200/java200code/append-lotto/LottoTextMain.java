
public class LottoTextMain {

	public static void main(String[] args) {
		
		LottoTextRead ltr=new LottoTextRead();
		//System.out.println(ltr.readTexts("lotto.txt"));
		String bases="<td width=70>1등 당첨자</td>";
		int intBases=ltr.readLineAt("lotto.txt",bases);  //310
		//System.out.println(ltr.readLineAt("lotto.txt",1145));
		//System.out.println(ltr.readLineAt("lotto.txt","<td width=70>1등 당첨자</td>"));
		//System.out.println("기준:  "+intBases);
		//System.out.println(ltr.readLineAt("lotto.txt",intBases+4));
		//String temp=ltr.readLineAt("lotto.txt",intBases+5);
		//System.out.println("토큰 :  "+ltr.howManyToken(temp,">"));
		//System.out.println(ltr.getLottoData(temp,">"));
		for(int i=0;i<105;i++){
			String [] aa=ltr.resultValues("lotto.txt",(intBases+4+i*9));
			//LottoTextRead.print(aa);
			try{
				System.out.println(ltr.getLottoVO(aa));
			}catch(Exception ee){
				System.out.println(ee.getMessage());
			}
		}
	}
}
