package kiea.z01.ztest.t01.NIO.t01;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;

import kiea.kr.co.tain.base.Flag;

public class GatheringTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws IOException
	{
		if (Flag.TRUE) {
			FileOutputStream fout = new FileOutputStream("D:/KANG.out");
			GatheringByteChannel channel = fout.getChannel();
			
			ByteBuffer header = ByteBuffer.allocateDirect(20);
			ByteBuffer body = ByteBuffer.allocateDirect(40);
			ByteBuffer[] buffers = { header, body };
			
			header.put("Hello, ".getBytes());
			body.put("World!".getBytes());
			
			header.flip();
			body.flip();
			
			channel.write(buffers);
			
			channel.close();
			fout.close();
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		if (Flag.TRUE) test01(args);
	}
}
