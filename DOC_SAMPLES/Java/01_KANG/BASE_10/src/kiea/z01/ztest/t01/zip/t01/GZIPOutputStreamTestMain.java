package kiea.z01.ztest.t01.zip.t01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import kiea.kr.co.tain.base.Flag;

public class GZIPOutputStreamTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		if (Flag.TRUE) {
			
			String fileName = "D:/gzip/test.txt";
			File f = new File(fileName);
			File g = new File(fileName + ".gz");
			FileInputStream fis = null;
			FileOutputStream fos = null;
			GZIPOutputStream gos = null;
			
			try {
				fis = new FileInputStream(f);
				fos = new FileOutputStream(g);
				gos = new GZIPOutputStream(fos);
				
				byte[] buf = new byte[1024];
				
				while (true) {
					int len = fis.read(buf, 0, buf.length);
					if (len < 0)
						break;
					gos.write(buf, 0, len);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fis != null) try { fis.close(); } catch (IOException e) {}
				if (fos != null) try { fos.close(); } catch (IOException e) {}
				if (gos != null) try { gos.close(); } catch (IOException e) {}
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01(args);
	}
}
