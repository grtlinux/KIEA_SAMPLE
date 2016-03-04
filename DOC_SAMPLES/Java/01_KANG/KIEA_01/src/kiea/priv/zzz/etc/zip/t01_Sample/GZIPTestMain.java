package kiea.priv.zzz.etc.zip.t01_Sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPTestMain
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		if (flag) {
			/*
			 * 압축한다.
			 */
			String fileName = "D:/gzip/test.txt";
			
			File inFile = new File(fileName);
			File outFile = new File(fileName + ".gz");
			
			FileInputStream fis = null;
			FileOutputStream fos = null;
			GZIPOutputStream gos = null;
			
			try {
				fis = new FileInputStream(inFile);
				fos = new FileOutputStream(outFile);
				gos = new GZIPOutputStream(fos);
				
				byte[] by = new byte[1024];
				while (true) {
					int len = fis.read(by, 0, by.length);
					if (len < 0)
						break;
					
					gos.write(by, 0, len);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fis != null) try { fis.close(); } catch (IOException e) {}
				if (gos != null) try { gos.close(); } catch (IOException e) {}
				if (fos != null) try { fos.close(); } catch (IOException e) {}
			}
		}
	}
	
	private static void test02(String[] args)
	{
		if (flag) {
			/*
			 * 압축푼다.
			 */
			String fileName = "D:/gzip/test.txt";
			
			File inFile = new File(fileName);
			File outFile = new File(fileName + ".gz");
			
			FileInputStream fis = null;
			FileOutputStream fos = null;
			GZIPInputStream gis = null;
			
			try {
				fis = new FileInputStream(inFile);
				fos = new FileOutputStream(outFile);
				gis = new GZIPInputStream(fis);
				
				byte[] by = new byte[1024];
				while (true) {
					int len = gis.read(by, 0, by.length);
					if (len < 0)
						break;
					
					fos.write(by, 0, len);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (gis != null) try { gis.close(); } catch (IOException e) {}
				if (fis != null) try { fis.close(); } catch (IOException e) {}
				if (fos != null) try { fos.close(); } catch (IOException e) {}
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (flag) test01(args);
		if (flag) test02(args);
	}
}
