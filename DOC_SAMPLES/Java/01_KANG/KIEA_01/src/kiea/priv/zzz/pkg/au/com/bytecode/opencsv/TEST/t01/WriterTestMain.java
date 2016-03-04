package kiea.priv.zzz.pkg.au.com.bytecode.opencsv.TEST.t01;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.output.FileWriterWithEncoding;

import au.com.bytecode.opencsv.CSVWriter;

public class WriterTestMain
{
	private static boolean flag = true;
	
	public static class WriteBean
	{
		private String fld1;
		private String fld2;
		private String fld3;
		private String fld4;
		private String fld5;
		private String fld6;
		private String fld7;
		private String fld8;
		private String fld9;
		
		private String strRow;
		
		private ByteBuffer buf = ByteBuffer.allocate(1500);
		
		public void setBuf()
		{
			if (flag) {
				
				buf.clear();
				
				buf.put("\"".getBytes());
				buf.put(fld1.getBytes());
				buf.put("\",\"".getBytes());
				buf.put(fld2.getBytes());
				buf.put("\",\"".getBytes());
				buf.put(fld3.getBytes());
				buf.put("\",\"".getBytes());
				buf.put(fld4.getBytes());
				buf.put("\",\"".getBytes());
				buf.put(fld5.getBytes());
				buf.put("\",\"".getBytes());
				buf.put(fld6.getBytes());
				buf.put("\",\"".getBytes());
				buf.put(fld7.getBytes());
				buf.put("\",\"".getBytes());
				buf.put(fld8.getBytes());
				buf.put("\",\"".getBytes());
				buf.put(fld9.getBytes());
				buf.put("\"\n".getBytes());
			}
		}
		
		public ByteBuffer getBuf()
		{
			buf.flip();
			return buf;
		}
		
		public void setStrRow()
		{
			// strRow = "123456789";
			
			if (flag) {
				StringBuffer sb = new StringBuffer();
				
				sb.append("\"").append(fld1).append("\",");
				sb.append("\"").append(fld2).append("\",");
				sb.append("\"").append(fld3).append("\",");
				sb.append("\"").append(fld4).append("\",");
				sb.append("\"").append(fld5).append("\",");
				sb.append("\"").append(fld6).append("\",");
				sb.append("\"").append(fld7).append("\",");
				sb.append("\"").append(fld8).append("\",");
				sb.append("\"").append(fld9).append("\"\n");
				
				strRow = sb.toString();
			}
		}
		
		public String getStrRow()
		{
			return strRow;
		}
		
		public String[] getStringArray()
		{
			List<String> list = new ArrayList<String>();
			list.add(fld1);
			list.add(fld2);
			list.add(fld3);
			list.add(fld4);
			list.add(fld5);
			list.add(fld6);
			list.add(fld7);
			list.add(fld8);
			list.add(fld9);

			return list.toArray(new String[list.size()]);
		}

		public String getFld1()
		{
			return fld1;
		}

		public void setFld1(String fld1)
		{
			this.fld1 = fld1;
		}

		public String getFld2()
		{
			return fld2;
		}

		public void setFld2(String fld2)
		{
			this.fld2 = fld2;
		}

		public String getFld3()
		{
			return fld3;
		}

		public void setFld3(String fld3)
		{
			this.fld3 = fld3;
		}

		public String getFld4()
		{
			return fld4;
		}

		public void setFld4(String fld4)
		{
			this.fld4 = fld4;
		}

		public String getFld5()
		{
			return fld5;
		}

		public void setFld5(String fld5)
		{
			this.fld5 = fld5;
		}

		public String getFld6()
		{
			return fld6;
		}

		public void setFld6(String fld6)
		{
			this.fld6 = fld6;
		}

		public String getFld7()
		{
			return fld7;
		}

		public void setFld7(String fld7)
		{
			this.fld7 = fld7;
		}

		public String getFld8()
		{
			return fld8;
		}

		public void setFld8(String fld8)
		{
			this.fld8 = fld8;
		}

		public String getFld9()
		{
			return fld9;
		}

		public void setFld9(String fld9)
		{
			this.fld9 = fld9;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		String fileName = null;
		
		if (flag) {
			if (args.length != 1) {
				System.out.println("USAGE : java WriterTestMain [fileName]");
				System.exit(-1);
			}
			
			fileName = args[0];
		}
		
		List<WriteBean> list = null;
		
		if (flag) {
			/*
			 * LIST 생성 - 1 (1)
			 */
			list = new ArrayList<WriteBean>();

			long time1 = System.nanoTime();

			for (int i=0; i < 100050; i++) {
				WriteBean bean = new WriteBean();
				
				bean.setFld1("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld2("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld3("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld4("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld5("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld6("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld7("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld8("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld9("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

				if (flag) bean.setStrRow();
				
				list.add(bean);
			}
			
			if (flag) System.out.println(String.format("## PART-1 (1) ## took time : %d ms", (System.nanoTime() - time1)/1000/1000));
		}
		
		if (flag) {
			/*
			 * LIST 생성 - 1 (2)
			 */
			list = new ArrayList<WriteBean>();

			long time1 = System.nanoTime();

			for (int i=0; i < 100050; i++) {
				WriteBean bean = new WriteBean();
				
				bean.setFld1("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld2("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld3("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld4("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld5("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld6("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld7("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld8("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
				bean.setFld9("ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

				if (flag) bean.setBuf();
				
				list.add(bean);
			}
			
			if (flag) System.out.println(String.format("## PART-1 (2) ## took time : %d ms", (System.nanoTime() - time1)/1000/1000));
		}
		
		if (flag) {
			/*
			 * CSV 생성 - 2
			 */
			long time1 = System.nanoTime();
			
			try {
				/*
				 * 기존 CSV 파일을 삭제하고 신규로 CSV를 생성하여 list를 기록한다.
				 */

				if (flag) new File(fileName).delete();
				
				CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(fileName, "EUC-KR"));  // default seperator

				for (WriteBean line : list) {
					writer.writeNext(line.getStringArray());
				}
				
				writer.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (flag) System.out.println(String.format("## PART-2 ## took time : %d ms", (System.nanoTime() - time1)/1000/1000));
		}
		
		if (flag) {
			/*
			 * CSV 생성 - 3
			 */
			long time1 = System.nanoTime();
			
			try {
				/*
				 * 기존 CSV 파일을 삭제하고 신규로 CSV를 생성하여 list를 기록한다.
				 */

				if (flag) new File(fileName).delete();
				
				CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(fileName, "EUC-KR"));  // default seperator

				int length = list.size();
				for (int i=0; i < length; i++) {
					writer.writeNext(list.get(i).getStringArray());
				}
				
				writer.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (flag) System.out.println(String.format("## PART-3 ## took time : %d ms", (System.nanoTime() - time1)/1000/1000));
		}

		if (flag) {
			/*
			 * CSV 생성 - 4
			 */
			long time1 = System.nanoTime();
			
			try {
				/*
				 * 기존 CSV 파일을 삭제하고 신규로 CSV를 생성하여 list를 기록한다.
				 */

				if (flag) new File(fileName).delete();
				
				PrintWriter pw = new PrintWriter(fileName, "EUC-KR");

				StringBuffer sb = null;
				int length = list.size();
				
				int cntBlock = 5000;
				
				for (int i=0; i < length; i++) {
					if (i % cntBlock == 0) {
						/*
						 * Block Write
						 */
						if (sb != null)
							pw.write(sb.toString());
						
						sb = new StringBuffer();
					}
					
					/*
					 * 레코드 처리 "fld1", "fld2" ...
					 */
					String[] fld = list.get(i).getStringArray();
					for (int j=0; j < fld.length; j++) {
						if (j > 0)
							sb.append(",");
						
						sb.append("\"").append(fld[j]).append("\"");
					}
					sb.append("\n");
				}
				
				if (sb != null)
					pw.write(sb.toString());
				
				pw.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (flag) System.out.println(String.format("## PART-4 ## took time : %d ms", (System.nanoTime() - time1)/1000/1000));
		}

		if (flag) {
			/*
			 * CSV 생성 - 5
			 */
			long time1 = System.nanoTime();
			
			try {
				/*
				 * 기존 CSV 파일을 삭제하고 신규로 CSV를 생성하여 list를 기록한다.
				 */

				if (flag) new File(fileName).delete();
				
				PrintWriter pw = new PrintWriter(fileName, "EUC-KR");

				StringBuffer sb = null;
				int length = list.size();
				
				int cntBlock = 500;
				
				for (int i=0; i < length; i++) {
					if (i % cntBlock == 0) {
						/*
						 * Block Write
						 */
						if (sb != null)
							pw.write(sb.toString());
						
						sb = new StringBuffer();
					}
					
					/*
					 * 레코드 처리 "fld1", "fld2" ...
					 */
					if (flag) sb.append(list.get(i).getStrRow());
					if (!flag) sb.append(list.get(i).getBuf());
				}
				
				if (sb != null)
					pw.write(sb.toString());
				
				pw.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (flag) System.out.println(String.format("## PART-5 ## took time : %d ms", (System.nanoTime() - time1)/1000/1000));
		}
		
		if (flag) {
			/*
			 * CSV 생성 - 6
			 */
			long time1 = System.nanoTime();
			
			try {
				/*
				 * 기존 CSV 파일을 삭제하고 신규로 CSV를 생성하여 list를 기록한다.
				 */

				if (flag) new File(fileName).delete();
				
				
				FileOutputStream fOut = new FileOutputStream(fileName);
				FileChannel out = fOut.getChannel();
				
				for (int i=0; i < list.size(); i++) {
					out.write(list.get(i).getBuf());
				}
				
				out.close();
				fOut.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (flag) System.out.println(String.format("## PART-6 ## took time : %d ms", (System.nanoTime() - time1)/1000/1000));
		}
		
		if (flag) {
			/*
			 * CSV 생성 - 7
			 */
			long time1 = System.nanoTime();
			
			ByteBuffer outBuf = ByteBuffer.allocate(512 * 1024 * 1024);
			
			for (int k=0; k < 100; k++) {
				outBuf.clear();
				for (int i=0; i < list.size(); i++) {
					outBuf.put(list.get(i).getBuf());
				}
			}
			
			if (flag) System.out.println(String.format("## PART-7 (1) ## took time : %d ms", (System.nanoTime() - time1)/1000/1000));
			time1 = System.nanoTime();
			
			try {
				/*
				 * 기존 CSV 파일을 삭제하고 신규로 CSV를 생성하여 list를 기록한다.
				 */

				if (flag) new File(fileName).delete();
				
				
				FileOutputStream fOut = new FileOutputStream(fileName);
				FileChannel out = fOut.getChannel();
				
				outBuf.flip();
				out.write(outBuf);
				
				out.close();
				fOut.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (flag) System.out.println(String.format("## PART-7 (2) ## took time : %d ms", (System.nanoTime() - time1)/1000/1000));
		}
	}

	public static void main(String[] args)
	{
		if (!flag) test01(args);
		if (flag) test01(new String[] { "D:/KANG.csv" });
	}
}
