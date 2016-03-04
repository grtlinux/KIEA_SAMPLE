package kiea.z01.ztest.t01.Format.DateFormat.SimpleDateFormat.v01;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import kiea.kr.co.tain.base.Flag;

public class SimpleDateFormatMain
{

	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			String[] parsePatterns = new String[] {
					"yyyy-MM-dd HH:mm:ss",
					"yyyy-MM-dd aaHH:mm:ss",
					"E, dd MMM yyyy HH:mm:ss Z",
					"yyyy-MM-dd'T'HH:mm:ss +SS",
			};
			
			String[] arrDate = new String[] {
					"2009-02-10 16:22:32",
					"2009-03-28 오전 10:02:47",
					"화, 19 5월 2009 20:39:42 +0900",
					"Tue, 21 May 2009 20:39:42 +0900",
					"2009-05-20T10:38:21 +09:00"
			};
			
			SimpleDateFormat parser = null;
			SimpleDateFormat odf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss, E", Locale.KOREA);
			
			ParsePosition pos = new ParsePosition(0);
			for (int i=0, ei = parsePatterns.length; i < ei; i++) {
				if (i == 0) {
					// location을 변경하여 테스트
					//parser = new SimpleDateFormat(parsePatterns[i], Locale.US);
					parser = new SimpleDateFormat(parsePatterns[i]);
				} else {
					parser.applyPattern(parsePatterns[i]);
				}
				
				for (int j=0, ej = arrDate.length; j < ej; j++) {
					pos.setIndex(0);
					Date date = parser.parse(arrDate[j], pos);
					if (date != null) {
						System.out.println(odf.format(date));
					}
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
