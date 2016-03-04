import java.io.*;
import java.util.*;
public class FileDirectoryMaking {

	public boolean make(String fname){//존재하지 않는 파일 만들기
		File f=new File(fname);
		if(f.isDirectory() && f.exists()){
			System.out.println(fname+"가 이미 존재함");
			return false;
		}
		f.setLastModified(new Date().getTime());//수정일
		return f.mkdir();
	}//
	public boolean renameTo(String fname, String newName){
		File f=new File(fname);//존재하는 파일이름 바꾸기
		if(!f.exists()){
			System.out.println(fname+"가 없다.");
			return false;
		}
		f.setLastModified(new Date().getTime());//수정일
		return f.renameTo(new File(newName));
	}//
	public boolean delete(String fname){
		File f=new File(fname);//존재하는 파일이름 바꾸기
		if(!f.exists()){
			System.out.println(fname+"가 없다.");
			return false;
		}
		//cf f.deleteOnExit();
		return f.delete();//
	}//
}
