package kiea.priv.zzz.etc.zip.t01_Sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;

public class CheckedStreamTestMain
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		if (flag) {
			/*
			 * Output Check
			 */
			try {
				File inFile = new File("aaa.jpg");
				File outFile = new File("aaa.jpg.dfl");
				
				FileInputStream fis = new FileInputStream(inFile);
				FileOutputStream fos = new FileOutputStream(outFile);
				
				CRC32 crc = new CRC32();  // Checksum
				CheckedOutputStream cos = new CheckedOutputStream(fos, crc);
				
				byte[] by = new byte[1024];
				while (true) {
					int len = fis.read(by, 0, by.length);
					if (len < 0)
						break;
					cos.write(by, 0, len);
				}
				
				fis.close();
				
				System.out.println("CRC32 = " + cos.getChecksum());
				System.out.println("CRC32 value = " + crc.getValue());
				
				cos.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test02(String[] args)
	{
		if (flag) {
			/*
			 * Input Check
			 */
			try {
				File inFile = new File("aaa.jpg.dfl");
				File outFile = new File("aaa.jpg");
				
				FileInputStream fis = new FileInputStream(inFile);
				FileOutputStream fos = new FileOutputStream(outFile);
				
				CRC32 crc = new CRC32();
				CheckedInputStream cis = new CheckedInputStream(fis, crc);
				
				byte[] by = new byte[1024];
				while (true) {
					int len = cis.read(by, 0, by.length);
					if (len < 0)
						break;
					fos.write(by, 0, len);
				}
				
				System.out.println("CRC32 = " + cis.getChecksum());
				System.out.println("CRC32 value = " + crc.getValue());
				
				cis.close();
				fos.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (flag) test01(args);
		if (flag) test02(args);
	}
}
