package kiea.z01.ztest.t01.zip.t01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import kiea.kr.co.tain.base.Flag;

public class ZipInputStreamTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		if (Flag.TRUE) {
			/*
			 * 압축 풀기
			 */

			try {
				File f = new File("zip.zip");
				FileInputStream fis = new FileInputStream(f);
				ZipInputStream zis = new ZipInputStream(fis);
				
				File f1 = new File("unzip");   // 폴더
				
				while (true) {
					ZipEntry ze = zis.getNextEntry();
					if (ze == null)
						break;
					
					// f1 폴더에 해당 이름으로 압축을 푼다.
					File ff = new File(f1, ze.getName());
					FileOutputStream fos = new FileOutputStream(ff);
					
					byte[] buf = new byte[1024];
					while (true) {
						int len = zis.read(buf, 0, buf.length);
						if (len < 0)
							break;
						fos.write(buf, 0, len);
					}
					
					fos.close();
					zis.closeEntry();
				}
				
				zis.close();
				
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
