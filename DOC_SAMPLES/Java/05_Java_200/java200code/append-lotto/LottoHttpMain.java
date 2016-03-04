
public class LottoHttpMain {

	public static void main(String[] args) {
		LottoHttp lotto=new LottoHttp();
		//LottoRead lr=new LottoRead();
		//System.out.println(lotto.doGet());
		LottoTextRead ltr=new LottoTextRead();
		ltr.setTexts("lotto.txt",lotto.doGet());
		System.out.println("done!!");
		//int a=lr.position(lotto.doGet(),LottoRead.ES1);
		//int b=lr.position(lotto.doGet(),"<td height=\"22\" bgcolor=\"#F8E381\">");
		//System.out.println(a);
		//System.out.println(b);
		/*
		System.out.println(lotto.whereLine("<html>"));
		System.out.println(lotto.whereLine("<td width=\"40\">È¦Â¦</td>"));
		System.out.println(lotto.whereLine("<td>³¡¼ö</td>"));
		System.out.println(lotto.whereAt(0).trim());
		System.out.println(lotto.whereAt(319).trim());
		System.out.println(lotto.whereAt(320).trim());
		*/
 		//int b=lr.position(lotto.doGet(),"<tr height=32 align=center bgcolor=#ffffff>");
	    //System.out.println(b);
		//System.out.println(lotto.whereAt(10524).trim());
	}
}
