package kiea.priv.zzz.etc.zip.t01_Sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class DeflaterInflaterTestMain
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		if (flag) {
			/*
			 * 압축 한다.
			 */
			try {
				File inFile = new File("aaa.ppt");
				File outFile = new File("aaa.ppt.dfl");
				
				FileInputStream fis = new FileInputStream(inFile);
				FileOutputStream fos = new FileOutputStream(outFile);
				
				DeflaterOutputStream dos = new DeflaterOutputStream(fos);
				
				byte[] by = new byte[1024];
				while (true) {
					int len = fis.read(by, 0, by.length);
					if (len < 0)
						break;
					dos.write(by, 0, len);
				}
				
				dos.finish();
				fos.close();
				fis.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test02(String[] args)
	{
		if (flag) {
			/*
			 * 압축 푼다.
			 */
			try {
				File inFile = new File("aaa.ppt.dfl");
				File outFile = new File("aaa.ppt");
				
				FileInputStream fis = new FileInputStream(inFile);
				FileOutputStream fos = new FileOutputStream(outFile);
				
				InflaterInputStream iis = new InflaterInputStream(fis);
				
				byte[] by = new byte[1024];
				while (true) {
					int len = iis.read(by, 0, by.length);
					if (len < 0)
						break;
					
					fos.write(by, 0, len);
				}
				
				fos.close();
				iis.close();
				fis.close();
				
				
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
