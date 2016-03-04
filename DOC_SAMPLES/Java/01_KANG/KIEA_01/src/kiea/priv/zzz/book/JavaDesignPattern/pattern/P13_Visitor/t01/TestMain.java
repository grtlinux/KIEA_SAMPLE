package kiea.priv.zzz.book.JavaDesignPattern.pattern.P13_Visitor.t01;

public class TestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		try {
			
			System.out.println("Making root entries...");
			
			Directory rootDir = new Directory("root");
			
			Directory binDir = new Directory("bin");
			Directory tmpDir = new Directory("tmp");
			Directory usrDir = new Directory("usr");
			
			rootDir.add(binDir);
			rootDir.add(tmpDir);
			rootDir.add(usrDir);
			
			binDir.add(new File("vi", 10000));
			binDir.add(new File("latex", 20000));
			
			rootDir.accept(new ListVisitor());
			
			System.out.println();
			System.out.println("Making user entries...");
			
			Directory kimDir = new Directory("Kim");
			Directory leeDir = new Directory("Lee");
			Directory kangDir = new Directory("Kang");
			
			usrDir.add(kimDir);
			usrDir.add(leeDir);
			usrDir.add(kangDir);

			kimDir.add(new File("diary.html", 100));
			kimDir.add(new File("Composite.java", 200));
			leeDir.add(new File("memo.txt", 300));
			kangDir.add(new File("game.doc", 400));
			kangDir.add(new File("junk.mail", 500));

			rootDir.accept(new ListVisitor());

		} catch (FileTreatmentException e) {
			e.printStackTrace();
		}
	}
}
