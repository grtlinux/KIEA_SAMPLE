package kiea.z01.ztest.t01.zip.t01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import kiea.kr.co.tain.base.Flag;

public class GZIPInputStreamTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		if (Flag.TRUE) {
			
			String fileName = "D:/gzip/test.txt";
			File g = new File(fileName + ".gz");
			File f = new File(fileName);
			FileInputStream fis = null;
			GZIPInputStream gis = null;
			FileOutputStream fos = null;
			
			try {
				fis = new FileInputStream(g);
				gis = new GZIPInputStream(fis);
				fos = new FileOutputStream(f);
				
				byte[] buf = new byte[1024];
				
				while (true) {
					int len = gis.read(buf, 0, buf.length);
					if (len < 0)
						break;
					fos.write(buf, 0, len);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fis != null) try { fis.close(); } catch (IOException e) {}
				if (gis != null) try { gis.close(); } catch (IOException e) {}
				if (fos != null) try { fos.close(); } catch (IOException e) {}
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01(args);
	}
}
