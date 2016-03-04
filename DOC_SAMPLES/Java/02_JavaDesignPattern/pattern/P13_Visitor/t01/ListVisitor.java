package kiea.priv.zzz.book.JavaDesignPattern.pattern.P13_Visitor.t01;


public class ListVisitor extends Visitor
{
	private String currentDir = "";
	
	public void visit(File file)
	{
		System.out.println(currentDir + "/" + file);
	}
	
	public void visit(Directory directory)
	{
		System.out.println(currentDir + "/" + directory);
		
		String saveDir = currentDir;
		currentDir = currentDir + "/" + directory.getName();
		
//		Iterator<Entry> iter = directory.iterator();
//		while (iter.hasNext()) {
//			Entry entry = iter.next();
//			entry.accept(this);
//		}
		
		for (Entry entry : directory.getList()) {
			entry.accept(this);
		}
		
		currentDir = saveDir;
	}
}
