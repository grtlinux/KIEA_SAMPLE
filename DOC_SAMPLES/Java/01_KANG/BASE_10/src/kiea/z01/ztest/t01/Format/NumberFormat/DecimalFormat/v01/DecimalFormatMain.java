package kiea.z01.ztest.t01.Format.NumberFormat.DecimalFormat.v01;

import java.text.DecimalFormat;
import java.util.Scanner;

import kiea.kr.co.tain.base.Flag;

public class DecimalFormatMain
{
	/*
	 * 예) val = 78.53981633
	 * 0.###  -> 78.54 (78.540이므로 0은 출력되지 않는다.)
	 * 000.## -> 078.54
	 * 00.#   -> 78.5
	 */
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			int radius;
			double area, circumference;
			
			if (Flag.TRUE) {
				@SuppressWarnings("resource")
				Scanner scan = new Scanner(System.in);
				
				System.out.print("원의 반지름을 입력하세요. :");
				radius = scan.nextInt();
			}
			
			if (Flag.TRUE) {
				area = Math.PI * Math.pow(radius, 2);
				circumference = 2 * Math.PI * radius;
				
				System.out.println("원의 넓이 : " + area);
				System.out.println("원의 길이 : " + circumference);
				System.out.println();
			}
			
			if (Flag.TRUE) {
				DecimalFormat fmt = new DecimalFormat("0.###");
				System.out.println("DecimalFormat(0.###) 적용 후");
				
				System.out.println("원의 넓이 : " + fmt.format(area));
				System.out.println("원의 길이 : " + fmt.format(circumference));
				System.out.println();
			}
			
			if (Flag.TRUE) {
				DecimalFormat fmt = new DecimalFormat("000.#");
				System.out.println("DecimalFormat(000.#) 적용 후");
				
				System.out.println("원의 넓이 : " + fmt.format(area));
				System.out.println("원의 길이 : " + fmt.format(circumference));
				System.out.println();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
