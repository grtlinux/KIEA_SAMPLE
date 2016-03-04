package kiea.priv.zzz.etc.zip.t02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipJobIdTestMain
{
	private static boolean flag = true;
	
	private static String folder = "D:/TEMP";
	private static String zipFolder = "D:/TEMP/ZIP";
	private static String unzipFolder = "D:/TEMP/UNZIP";
	private static String jobId = "AR010141230A3067";
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		if (flag) {
			/*
			 * 폴더에 대한 처리
			 */
			
			try {
				String strFolder = zipFolder + "/" + jobId;

				File fileFolder = new File(strFolder);
				File[] listFiles = fileFolder.listFiles();
				
				for (File file : listFiles) {
					if (!flag) System.out.println("# " + file.getPath() + ", " + file.getName() + ", " + file.getAbsolutePath() + ", " + file.getAbsoluteFile() + ", " + file.getCanonicalPath() + ", " + file.getCanonicalFile());
					if (flag) System.out.println("# " + file.getPath() + ", " + file.getParent() + ", " + file.getName());
					
					if (file.isDirectory()) {
						throw new Exception("ERROR : is a directory...");
					} else if (file.isFile()) {
						if (flag) System.out.println("\t is csv file.. size = " + file.length());
					} else {
						throw new Exception("ERROR : is unknown file");
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test11(String[] args)
	{
		if (flag) {
			/*
			 * 여러개 파일을 한개의 파일로 압축한다.
			 */
			try {
				String outZipFile = folder + "/" + jobId + ".zip";

				ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outZipFile));
				zos.setMethod(8);
				zos.setLevel(9);
				/*
				 * 옵션
				 * setMethod : 0(압축안함) 8(압축)
				 * setLevel : -1(기본) 0~9(9가 압축율 높음)
				 */

				String strFolder = zipFolder + "/" + jobId;

				File fileFolder = new File(strFolder);
				File[] listFiles = fileFolder.listFiles();
				
				for (File file : listFiles) {
					if (flag) System.out.println("ZIP > " + file.getPath() + ", size = " + file.length());
					
					if (file.isFile()) {
						
						zos.putNextEntry(new ZipEntry(jobId + "/" + file.getName()));  // 확인 필요
							
						FileInputStream fis = new FileInputStream(file);
						
						byte[] by = new byte[1024];
						while (true) {
							int len = fis.read(by, 0, by.length);
							if (len < 0)
								break;
							
							zos.write(by, 0, len);
						}
						
						fis.close();
						
						zos.closeEntry();
					}
				}
				
				zos.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test12(String[] args)
	{
		if (!flag) {
			/*
			 * 압축파일의 목록을 본다. - 1
			 */
			try {
				String zipFile = folder + "/" + jobId + ".zip";
				
				ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
				
				while (true) {
					ZipEntry zipEntry = zis.getNextEntry();
					if (zipEntry == null)
						break;
					
					if (flag) System.out.println("LIST > " + zipEntry.getName());
					
					zis.closeEntry();
				}
				
				zis.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (flag) {
			/*
			 * 압축파일의 목록을 본다. - 2
			 */
			try {
				String zipFileName = folder + "/" + jobId + ".zip";
				
				@SuppressWarnings("resource")
				Enumeration<? extends ZipEntry> entries = new ZipFile(zipFileName).entries();
				while (entries.hasMoreElements()) {
					ZipEntry entry = entries.nextElement();
					if (flag) System.out.println("LIST > " + entry.getName());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test13(String[] args)
	{
		if (flag) {
			/*
			 * 한개의 압축파일을 여러개의 원본파일로 푼다.
			 */
			try {
				String inFile = folder + "/" + jobId + ".zip";
				String outFolder = unzipFolder;
				
				new File(unzipFolder + "/" + jobId + "/KANG/Hello").mkdirs();
				
				ZipInputStream zis = new ZipInputStream(new FileInputStream(inFile));
				
				while (true) {
					ZipEntry zipEntry = zis.getNextEntry();
					if (zipEntry == null)
						break;
					
					FileOutputStream fos = new FileOutputStream(new File(outFolder, zipEntry.getName()));
					
					if (flag) System.out.println("UNZIP > " + zipEntry.getName());

					byte[] by = new byte[1024];
					while (true) {
						int len = zis.read(by, 0, by.length);
						if (len < 0)
							break;
						
						fos.write(by, 0, len);
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
		if (!flag) test01(args);  // folder 확인
		if (flag) test11(args);  // 여러파일 압축
		if (flag) test12(args);  // 압축 목록 보기
		if (flag) test13(args);  // 압축 푼다.
	}
}
