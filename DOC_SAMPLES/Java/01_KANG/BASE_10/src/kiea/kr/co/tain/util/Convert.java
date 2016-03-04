package kiea.kr.co.tain.util;

import java.net.InetAddress;

import kiea.kr.co.tain.base.Flag;

public class Convert
{
	public static int byte2int(byte[] src)
	{
		int dst1 = src[0] & 0xFF;
		int dst2 = src[1] & 0xFF;
		int dst3 = src[2] & 0xFF;
		int dst4 = src[3] & 0xFF;
		
		return ((dst1 << 24) + (dst2 << 16) + (dst3 << 8) + (dst4 << 0));
	}
	
	public static byte[] int2byte(int num)
	{
		byte[] dst = new byte[4];
		
		if (!Flag.TRUE) {
			dst[0] = (byte) ((num >> 24) & 0xFF);
			dst[1] = (byte) ((num >> 16) & 0xFF);
			dst[2] = (byte) ((num >>  8) & 0xFF);
			dst[3] = (byte) ((num >>  0) & 0xFF);
		}
		
		if (Flag.TRUE) {
			dst[0] = (byte) ((num & 0xFF000000) >> 24);
			dst[1] = (byte) ((num & 0x00FF0000) >> 16);
			dst[2] = (byte) ((num & 0x0000FF00) >>  8);
			dst[3] = (byte) ((num & 0x000000FF) >>  0);
		}
		
		return dst;
	}

	public static byte[] long2byte(long num)
	{
		byte[] dst = new byte[8];
		
		dst[0] = (byte) ((num >>> 56) & 0xFF);
		dst[1] = (byte) ((num >>> 48) & 0xFF);
		dst[2] = (byte) ((num >>> 40) & 0xFF);
		dst[3] = (byte) ((num >>> 32) & 0xFF);
		dst[4] = (byte) ((num >>> 24) & 0xFF);
		dst[5] = (byte) ((num >>> 16) & 0xFF);
		dst[6] = (byte) ((num >>>  8) & 0xFF);
		dst[7] = (byte) ((num >>>  0) & 0xFF);
		
		return dst;
	}

	public static int[] byte2arrInt(byte[] bytes)
	{
		int[] ints = new int[bytes.length];
		
		for (int i=0; i < bytes.length; i++) {
			ints[i] = bytes[i] & 0xFF;
		}
		
		return ints;
	}
	
	public static String splitIndex(String src, String token, int index) throws ArrayIndexOutOfBoundsException
	{
		String[] arrItem = src.split(token);
		
		if (index < 0 || arrItem.length <= index)
			throw new ArrayIndexOutOfBoundsException();
		
		return arrItem[index].trim();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			try {
				InetAddress inet = InetAddress.getByName("125.209.222.141");
				byte[] bytes = inet.getAddress();
				int[] ints = byte2arrInt(bytes);
				
				StringBuffer sb = new StringBuffer();
				for (int num : ints) {
					sb.append(".");
					sb.append(num);
				}
				
				System.out.println(">" + sb.toString());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test02()
	{
		if (!Flag.TRUE) {
			String str = "  11.94.177.34   ,    12.91.60.91 ,  \t     12.91.60.94 ";
			String[] arrItem = str.split(",");
			for (int i=0; i < arrItem.length; i++)
				arrItem[i] = arrItem[i].trim();
			
			for (String item : arrItem) {
				System.out.println("[" + item + "]");
			}
		}
		
		if (Flag.TRUE) {
			String str = "  11.94.177.34   ,    12.91.60.91 ,  \t     12.91.60.94 ";
			
			System.out.println("[" + Convert.splitIndex(str, ",", 1) + "]");
			System.out.println("[" + Convert.splitIndex(str, ",", 4) + "]");
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();
	}
}
