package kiea.z01.ztest.t01.NIO.t01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import kiea.kr.co.tain.base.Flag;

public class WriteReadTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws IOException
	{
		if (Flag.TRUE) {
			
			String str = "Hello NIO Example...";
			byte[] by = str.getBytes();  // 버퍼에 문자열 str를 담기 위해 바이트 배열로 바꾼다.
			ByteBuffer buf = ByteBuffer.allocate(by.length);
			buf.put(by);
			buf.clear(); // position = 0, limit = capacity
			
			FileOutputStream fout = new FileOutputStream("D:/WriteRead.txt");
			FileChannel out = fout.getChannel();
			out.write(buf);
			out.close();
			fout.close();
			
			FileInputStream fin = new FileInputStream("D:/WriteRead.txt");
			FileChannel in = fin.getChannel();
			
			ByteBuffer buf2 = ByteBuffer.allocate((int) in.size());
			in.read(buf2);
			fin.close();

			byte[] buffer = buf2.array();
			str = new String(buffer);
			
			System.out.println(str);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		if (Flag.TRUE) test01(args);
	}
}
