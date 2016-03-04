package kiea.priv.zzz.book.CookBook.t01.com.darwinsys.io;

import java.io.IOException;

import kiea.priv.zzz.book.CookBook.t01.demo.com.darwinsys.io.FileIO;

public class FileIoDemo
{
	public static void main(String[] args)
	{
		try {
			FileIO.copyFile("FileIO.java", "FileIO.bak");
			FileIO.copyFile("FileIO.class", "FileIO-class.bak");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
