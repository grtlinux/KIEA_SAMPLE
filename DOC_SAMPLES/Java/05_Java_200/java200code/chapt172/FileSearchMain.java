public class FileSearchMain {

	public static void main(String[] args) {
		FileSearch fs=new FileSearch();
		try{
			fs.printDirectory("c:\\");//"C:\"
			//fs.printDirectory("FileSearch.java");
			//fs.printDirectory("C:\\WINDOWS");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
