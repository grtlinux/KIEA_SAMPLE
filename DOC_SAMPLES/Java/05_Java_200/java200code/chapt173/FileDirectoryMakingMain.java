public class FileDirectoryMakingMain {

	public static void main(String[] args) {
		FileDirectoryMaking fm=new FileDirectoryMaking();
		System.out.println(fm.make("aaa"));
		System.out.println(fm.make("bbb"));
		System.out.println(fm.renameTo("aaa","ccc"));
		System.out.println(fm.delete("ccc"));
	}
}
