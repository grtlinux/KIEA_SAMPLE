package kiea.z01.ztest.t01.zip.t01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;

import kiea.kr.co.tain.base.Flag;

public class CheckedStreamTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		if (Flag.TRUE) {
			/*
			 * Output Check
			 */
			try {
				File f = new File("aaa.jpg");
				File f1 = new File("aaa.jpg.dfl");
				
				FileInputStream fis = new FileInputStream(f);
				FileOutputStream fos = new FileOutputStream(f1);
				CRC32 crc = new CRC32(); // Checksum
				CheckedOutputStream cos = new CheckedOutputStream(fos, crc);
				
				byte[] buf = new byte[1024];
				while (true) {
					int len = fis.read(buf, 0, buf.length);
					if (len < 0)
						break;
					cos.write(buf, 0, len);
				}
				
				fis.close();
				System.out.println("CRC32 = " + cos.getChecksum());
				System.out.println("CRC32 value =" + crc.getValue());
				cos.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test02(String[] args)
	{
		if (Flag.TRUE) {
			/*
			 * Input Check
			 */
			try {
				File f = new File("aaa.jpg.dfl");
				File f1 = new File("aaa.jpg");
				
				FileInputStream fis = new FileInputStream(f);
				CRC32 crc = new CRC32();
				CheckedInputStream cis = new CheckedInputStream(fis, crc);
				FileOutputStream fos = new FileOutputStream(f1);
				
				byte[] buf = new byte[1024];
				while (true) {
					int len = cis.read(buf, 0, buf.length);
					if (len < 0)
						break;
					fos.write(buf, 0, len);
				}
				
				System.out.println("CRC32 = " + cis.getChecksum());
				System.out.println("CRC32 value =" + crc.getValue());
				cis.close();
				fos.close();
				
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
