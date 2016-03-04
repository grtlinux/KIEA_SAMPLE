package kiea.priv.zzz.book.JavaDesignPattern.pattern.P08_AbstractFactory.t01;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Vector;

public abstract class Page
{
	private boolean flag = true;
	
	protected String title;
	protected String author;
	protected Vector<Item> content = new Vector<Item>();
	
	public Page(String title, String author)
	{
		this.title = title;
		this.author = author;
	}
	
	public void add(Item item)
	{
		content.add(item);
	}
	
	public void output()
	{
		if (!flag) {
			try {
				String fileName = title + ".html";
				Writer writer = new FileWriter(fileName);
				writer.write(this.makeHTML());
				writer.close();
				System.out.println(fileName + " 을 작성했습니다.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (flag) {
			System.out.println(this.makeHTML());
		}
	}
	
	public abstract String makeHTML();
}
