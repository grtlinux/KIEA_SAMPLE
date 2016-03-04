package kiea.priv.zzz.book.JavaDesignPattern.pattern.P23_Interpreter.t01;

import java.io.BufferedReader;
import java.io.FileReader;

public class TestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		String fileName = "D:/KANG.20141230/WORK/workspace(64)/KIEA_01/src/kiea/priv/zzz/book/JavaDesignPattern/pattern/P23_Interpreter/t01/program.txt";
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			
			String text;
			while ((text = reader.readLine()) != null) {
				System.out.println("text = \"" + text + "\"");

				Node node = new ProgramNode();
				node.parse(new Context(text));
				
				System.out.println("node = " + node);
			}
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
