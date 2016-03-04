public class FileCommentsNumberMain {
    
	public static void main(String[] args) {
		FileCommentsNumber fc=new FileCommentsNumber("ChatServer");
		fc.fileRWInAnyType();//확장자가 있건 없건,  java, txt이라도 된다.
		//fc.fileReadAndWrite();//확장자가 java 만, 확장자가 없거나 
		//fc.txtReadAndWrite();//확장자가 txt
		//System.out.println(fc.makeFName("FileComments.java"));
		//System.out.println(fc.makeFName("FileComments.java"));
	}
}


