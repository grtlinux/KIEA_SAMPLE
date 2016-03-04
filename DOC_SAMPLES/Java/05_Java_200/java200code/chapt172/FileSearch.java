import java.io.*;
import java.util.*;
public class FileSearch {
	public void printDirectory(String fname)throws IOException{
		File f=new File(fname);
		if(f.exists()){
			System.out.println("절대경로: "+f.getAbsolutePath());//
			System.out.println("(대문자)절대경로: "+f.getCanonicalPath());//throws
			System.out.println("수정날짜: "+new Date(f.lastModified()));//수정날짜
			System.out.println("Read가능? "+f.canRead());//
			System.out.println("Write가능? "+f.canWrite());//
			if(f.isDirectory()){
				System.out.println("디렉토리인가? "+f.isDirectory());//
				File []fs= f.listFiles();
				for(int i=0; i<fs.length;i++){
					if(fs[i].isDirectory()){
						System.out.println(fs[i].getAbsoluteFile());//절대경로
					}
				}
			}else{
				System.out.println("파일이름 : "+f.getName());//파일이름
				System.out.println("파일 길이 : "+f.length()+" byte");//파일길이
				System.out.println("숨긴 파일인가? "+f.isHidden());
				System.out.println("파일구분문자 "+File.pathSeparatorChar);
				System.out.println("경로구분문자 "+File.separator);//경로구분문자
			}
		}else{
			System.out.println(fname+"이 없다.");
			System.exit(1);
		}
	}
}
