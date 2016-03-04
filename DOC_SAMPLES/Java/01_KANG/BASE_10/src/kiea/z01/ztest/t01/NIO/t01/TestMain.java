package kiea.z01.ztest.t01.NIO.t01;

import java.io.IOException;
import java.nio.ByteBuffer;

import kiea.kr.co.tain.base.Flag;

public class TestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws IOException
	{
		if (Flag.TRUE) {
			ByteBuffer buf = ByteBuffer.allocateDirect(2000000);
			
			buf.clear();
			buf.put("\"".getBytes());
			buf.put("Hello, ".getBytes());
			buf.put("\",\"".getBytes());
			buf.put("World !!(강석)".getBytes());
			buf.put("\"".getBytes());
			
			buf.clear();
			byte[] byteBuf = new byte[2000000];
			buf.get(byteBuf);
			
			System.out.println(new String(byteBuf));
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		if (Flag.TRUE) test01(args);
	}
}
