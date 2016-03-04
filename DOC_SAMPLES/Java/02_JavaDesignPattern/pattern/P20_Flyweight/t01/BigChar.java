package kiea.priv.zzz.book.JavaDesignPattern.pattern.P20_Flyweight.t01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BigChar
{
	private static final String PATH = "D:/KANG.20141230/WORK/workspace(64)/KIEA_01/src/kiea/priv/zzz/book/JavaDesignPattern/pattern/P20_Flyweight/chars/";
	
	private char charName;
	private String fontData;
	
	public BigChar(char charName)
	{
		this.charName = charName;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(PATH + "big" + this.charName + ".txt"));
			
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			
			reader.close();
			
			this.fontData = sb.toString();
		} catch (IOException e) {
			this.fontData = charName + "?";
		}
	}
	
	public void print()
	{
		System.out.println(fontData);
	}
}
