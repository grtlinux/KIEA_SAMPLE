package kiea.priv.zzz.book.JavaDesignPattern.pattern.P11_Composite.t02;

public class TestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		try {
			Directory rootDir = new Directory("root");

			Directory usrDir = new Directory("usr");
			rootDir.add(usrDir);
			
			Directory kimDir = new Directory("Kim");
			usrDir.add(kimDir);

			File file = new File("Composite.java", 100);
			kimDir.add(file);
			rootDir.printList();
			
			System.out.println();
			System.out.println("file = " + file.getFullName());
			System.out.println("kim = " + kimDir.getFullName());
			
		} catch (FileTreatmentException e) {
			e.printStackTrace();
		}
	}
}
