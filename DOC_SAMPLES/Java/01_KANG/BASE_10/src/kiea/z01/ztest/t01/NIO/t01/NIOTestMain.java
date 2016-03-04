package kiea.z01.ztest.t01.NIO.t01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import kiea.kr.co.tain.base.Flag;

public class NIOTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		if (Flag.TRUE) {
			/*
			 * Normal IO
			 */
			try {
				File inFile = new File("D:/KANG_IN.csv");
				File outFile = new File("D:/KANG_OUT.csv");
				
				long time1 = System.currentTimeMillis();
				
				InputStream is = new FileInputStream(inFile);
				FileOutputStream fos = new FileOutputStream(outFile);
				
				byte[] buf = new byte[64 * 1024];
				int len = 0;
				
				while ((len = is.read(buf)) != -1) {
					fos.write(buf, 0, len);
				}
				
				fos.flush();
				fos.close();
				is.close();
				
				System.out.println("Normal IO Time taken : " + (System.currentTimeMillis() - time1) + " ms");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test02(String[] args)
	{
		if (Flag.TRUE) {
			/*
			 * FileChannel
			 */
			try {
				File inFile = new File("D:/KANG_IN.csv");
				File outFile = new File("D:/KANG_OUT.csv");
				
				long time1 = System.currentTimeMillis();
				
				FileInputStream fis = new FileInputStream(inFile);
				FileOutputStream fos = new FileOutputStream(outFile);
				FileChannel fi = fis.getChannel();
				FileChannel fo = fos.getChannel();
				
				ByteBuffer buf = ByteBuffer.allocateDirect(64 * 1024);
				while (fi.read(buf) != -1) {
					buf.flip();
					fo.write(buf);
					buf.clear();
				}
				
				fos.close();
				fis.close();
				
				System.out.println("FileChannel IO Time taken : " + (System.currentTimeMillis() - time1) + " ms");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01(args);
		if (Flag.TRUE) test02(args);
	}
}
