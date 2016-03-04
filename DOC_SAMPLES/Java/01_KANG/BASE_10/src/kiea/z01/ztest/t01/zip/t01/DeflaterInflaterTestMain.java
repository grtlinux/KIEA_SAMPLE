package kiea.z01.ztest.t01.zip.t01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import kiea.kr.co.tain.base.Flag;

public class DeflaterInflaterTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		if (Flag.TRUE) {
			/*
			 * 압축 한다
			 */
			try {
				File f = new File("abc.ppt");
				File f1 = new File("abc.dfl");
				
				FileInputStream fis = new FileInputStream(f);
				FileOutputStream fos = new FileOutputStream(f1);
				
				DeflaterOutputStream dos = new DeflaterOutputStream(fos);
				
				byte[] buf = new byte[1024];
				while (true) {
					int len = fis.read(buf, 0, buf.length);
					if (len < 0)
						break;
					
					dos.write(buf, 0, len);
				}
				fis.close();
				dos.finish();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test02(String[] args)
	{
		if (Flag.TRUE) {
			/*
			 * 압축 푼다.
			 */
			try {
				File f = new File("abc.dfl");
				File f1 = new File("abc.ppt");
				
				FileInputStream fis = new FileInputStream(f);
				FileOutputStream fos = new FileOutputStream(f1);
				
				InflaterInputStream iis = new InflaterInputStream(fis);
				
				byte[] buf = new byte[1024];
				while (true) {
					int len = iis.read(buf, 0, buf.length);
					if (len < 0)
						break;
					
					fos.write(buf, 0, len);
				}
				iis.close();
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
