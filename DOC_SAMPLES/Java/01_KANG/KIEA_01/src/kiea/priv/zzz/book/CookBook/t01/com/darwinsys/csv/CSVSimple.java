package kiea.priv.zzz.book.CookBook.t01.com.darwinsys.csv;

import java.util.List;

import kiea.priv.zzz.book.CookBook.t01.demo.com.darwinsys.csv.CSVImport;

public class CSVSimple
{
	public static void main(String[] args)
	{
		CSVImport parser = new CSVImport();
		List<String> list = parser.parse("\"LU\",86.25,\"11/4/1998\",\"2:19PM\",+4.0625");
		for (String word : list) {
			System.out.println(word);
		}
		
		// Now try with a non-default separator
		parser = new CSVImport('|');
		list = parser.parse("\"LU\"|86.25|\"11/4/1998\"|\"2:19PM\"|+4.0625");
		for (String word : list) {
			System.out.println(word);
		}
	}
}
