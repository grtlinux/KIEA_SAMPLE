package kiea.z01.ztest.t01.zip.t01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import kiea.kr.co.tain.base.Flag;

public class ZipOutputStreamTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		if (Flag.TRUE) {
			/*
			 * 압축 하기
			 */
			
			String[] files = new String[] { "D:/zipfile/a.txt", "D:/zipfile/b.txt" };
			String zipFile = "D:/ab.zip";
			
			byte[] buf = new byte[1024];
			
			try {
				ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
				
				/*
				 * 옵션
				 * setMethod : 0(압축안함) 8(압축)
				 * setLevel : -1(기본) 0~9 (9가 압축율 높음)
				 */
				out.setMethod(8);
				out.setLevel(9);
				
				for (String file : files) {
					FileInputStream in = new FileInputStream(file);
					
					out.putNextEntry(new ZipEntry(file));
					
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
					
					out.closeEntry();
					in.close();
				}
				
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01(args);
	}
}
