/* Copyright (c) 2008-2014, KangSeok
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG,
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package kiea.priv.zzz.etc.scheduler.t01;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name DateTime.java
 *
 */
public class DateTime
{
	private static boolean flag = true;
	
	private static SimpleDateFormat df01 = null;
	private static SimpleDateFormat df02 = null;
	private static SimpleDateFormat df03 = null;
	private static SimpleDateFormat df04 = null;
	private static SimpleDateFormat df05 = null;
	private static SimpleDateFormat df06 = null;
	
	private static SimpleDateFormat tf01 = null;
	private static SimpleDateFormat tf02 = null;
	private static SimpleDateFormat tf03 = null;
	private static SimpleDateFormat tf04 = null;
	private static SimpleDateFormat tf05 = null;
	private static SimpleDateFormat tf06 = null;
	
	private static SimpleDateFormat dtf01 = null;
	private static SimpleDateFormat dtf02 = null;
	private static SimpleDateFormat dtf03 = null;
	private static SimpleDateFormat dtf04 = null;
	private static SimpleDateFormat dtf05 = null;
	
	static {
		try {
			df01 = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREA);
			df02 = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
			df03 = new SimpleDateFormat("yy/MM/dd", Locale.KOREA);
			df04 = new SimpleDateFormat("MM/dd", Locale.KOREA);
			df05 = new SimpleDateFormat("MMdd", Locale.KOREA);
			df06 = new SimpleDateFormat("yyMMdd", Locale.KOREA);
			
			tf01 = new SimpleDateFormat("HH:mm:ss.SSS", Locale.KOREA);
			tf02 = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
			tf03 = new SimpleDateFormat("HHmmssSSS", Locale.KOREA);
			tf04 = new SimpleDateFormat("HHmmss", Locale.KOREA);
			tf05 = new SimpleDateFormat("HHmm", Locale.KOREA);
			tf06 = new SimpleDateFormat("HH:mm ss", Locale.KOREA);
			
			dtf01 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS", Locale.KOREA);
			dtf02 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.KOREA);
			dtf03 = new SimpleDateFormat("yyyyMMdd HHmmssSSS", Locale.KOREA);
			dtf04 = new SimpleDateFormat("yyyyMMdd HHmmss", Locale.KOREA);
			dtf05 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 날짜 형식을 얻는다.
	 * @param type
	 * @return
	 */
	public static String getDate(int type)
	{
		if (flag) {
			switch (type) {
				case 1:
					return df01.format(new Date());
				case 2:
					return df02.format(new Date());
				case 3:
					return df03.format(new Date());
				case 4:
					return df04.format(new Date());
				case 5:
					return df05.format(new Date());
				case 6:
					return df06.format(new Date());
				default:
					return df01.format(new Date());
			}
		}
		
		return null;
	}
	
	/**
	 * 시간 형식을 얻는다.
	 * @param type
	 * @return
	 */
	public static String getTime(int type)
	{
		if (flag) {
			switch (type) {
				case 1:
					return tf01.format(new Date());
				case 2:
					return tf02.format(new Date());
				case 3:
					return tf03.format(new Date());
				case 4:
					return tf04.format(new Date());
				case 5:
					return tf05.format(new Date());
				case 6:
					return tf06.format(new Date());
				default:
					return tf01.format(new Date());
			}
		}
		
		return null;
	}
	
	/**
	 * 일시 형식을 얻는다.
	 * @param type
	 * @return
	 */
	public static String getDateTime(int type)
	{
		if (flag) {
			switch (type) {
				case 1:
					return dtf01.format(new Date());
				case 2:
					return dtf02.format(new Date());
				case 3:
					return dtf03.format(new Date());
				case 4:
					return dtf04.format(new Date());
				case 5:
					return dtf05.format(new Date());
				default:
					return dtf01.format(new Date());
			}
		}
		
		return null;
	}
	
	/**
	 * format 에 의한 일시를 구한다.
	 * @param format
	 * @return
	 */
	public static String getDateTime(String format)
	{
		String ret = null;
		
		if (flag) {
			ret = new SimpleDateFormat(format, Locale.KOREA).format(new Date());
		}
		
		return ret;
	}
	
	/**
	 * 오늘 날짜에 더한 날짜를 format 일시를 구한다.
	 * @param numAdd
	 * @param format
	 * @return
	 */
	public static String addToday(int numAdd, String format)
	{
		String ret = null;
		
		if (flag) {
			/*
			 * 오늘날짜를 milliseconds 단위로 구하고 연산한다.
			 */
			Date dt = new Date();
			long msec = dt.getTime();
			msec += (long)numAdd * 1000 * 60 * 60 * 24;
			dt.setTime(msec);

			ret = new SimpleDateFormat(format, Locale.KOREA).format(dt);
		}
		
		return ret;
	}
	
	/**
	 * 특정날짜에 년별 월별 일별 더하기 연산을 한다.
	 * @param strDate
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String addDate(String strDate, int year, int month, int day)
	{
		String ret = null;
		
		if (flag) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
				sdf.parse(strDate);
				
				Calendar cal = sdf.getCalendar();
				cal.add(Calendar.YEAR, year);
				cal.add(Calendar.MONTH, month);
				cal.add(Calendar.DAY_OF_MONTH, day);
				
				ret = sdf.format(cal.getTime());
				
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	/**
	 * 특정날짜에 년 더하기 연산을 한다.
	 * @param strDate
	 * @param year
	 * @return
	 */
	public static String addYears(String strDate, int years)
	{
		return addDate(strDate, years, 0, 0);
	}
	
	/**
	 * 특정날짜에 월 더하기 연산을 한다.
	 * @param strDate
	 * @param month
	 * @return
	 */
	public static String addMonths(String strDate, int months)
	{
		return addDate(strDate, 0, months, 0);
	}
	
	/**
	 * 특정날짜에 일 더하기 연산을 한다.
	 * @param strDate
	 * @param day
	 * @return
	 */
	public static String addDays(String strDate, int days)
	{
		return addDate(strDate, 0, 0, days);
	}
	
	/**
	 * 날짜형 문자열이 맞는지 확인한다.
	 * @param strDate
	 * @return
	 */
	public static boolean checkDate(String strDate)
	{
		boolean ret = false;
		
		if (flag) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
				if (sdf.format(sdf.parse(strDate)).equals(strDate))
					return true;
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	/**
	 * 
	 * @param strDate
	 * @return
	 */
	public static boolean isDateStr(String strDate)
	{
		return checkDate(strDate);
	}
	
	/**
	 * 
	 * diffDates
	 *
	 * @param strDate1
	 * @param strDate2
	 * @return
	 */
	public static int diffDates(String strDate1, String strDate2)
	{
		int ret = 0;
		
		if (flag) {
			try {
				if (flag) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
					Date dt1 = sdf.parse(strDate1);
					Date dt2 = sdf.parse(strDate2);
					long val1 = dt1.getTime();
					long val2 = dt2.getTime();
					
					long diff = Math.abs(val1 - val2);
					
					long diffDays = diff / (24 * 60 * 60 * 1000);
					
					ret = (int) diffDays;
				} else {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
					ret = (int) (Math.abs(sdf.parse(strDate1).getTime() - sdf.parse(strDate2).getTime()) / (24 * 60 * 60 * 1000));
				}
				
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	public static long addSeconds(Date date, long sec)
	{
		long ret = 0;
		
		if (flag) {
			ret = date.getTime();
			ret += (sec * 1000);
		}
		
		return ret;
	}
	
	public static long addMinutes(Date date, long min)
	{
		return addSeconds(date, min * 60);
	}
	
	public static long addHours(Date date, long hour)
	{
		return addMinutes(date, hour * 60);
	}
	
	public static long getMilliSeconds(String str)
	{
		long ret = 0;
		
		if (flag) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

				Date date = sdf.parse(str);
				ret = date.getTime();
				if (!flag) System.out.println("[" + ret + "]");
			} catch (ParseException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		
		return ret;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * test-01
	 */
	private static void test01()
	{
		if (!flag) {
			/*
			 * 날짜의 기본 포맷을 확인
			 */
			System.out.println(String.format("DateTime.getDate(*) [%s]", DateTime.getDate(0)));
			System.out.println(String.format("DateTime.getDate(1) [%s]", DateTime.getDate(1)));
			System.out.println(String.format("DateTime.getDate(2) [%s]", DateTime.getDate(2)));
			System.out.println(String.format("DateTime.getDate(3) [%s]", DateTime.getDate(3)));
			System.out.println(String.format("DateTime.getDate(4) [%s]", DateTime.getDate(4)));
			System.out.println(String.format("DateTime.getDate(5) [%s]", DateTime.getDate(5)));

			System.out.println(String.format("DateTime.getTime(*) [%s]", DateTime.getTime(0)));
			System.out.println(String.format("DateTime.getTime(1) [%s]", DateTime.getTime(1)));
			System.out.println(String.format("DateTime.getTime(2) [%s]", DateTime.getTime(2)));
			System.out.println(String.format("DateTime.getTime(3) [%s]", DateTime.getTime(3)));
			System.out.println(String.format("DateTime.getTime(4) [%s]", DateTime.getTime(4)));
			System.out.println(String.format("DateTime.getTime(5) [%s]", DateTime.getTime(5)));

			System.out.println(String.format("DateTime.getDateTime(*) [%s]", DateTime.getDateTime(0)));
			System.out.println(String.format("DateTime.getDateTime(1) [%s]", DateTime.getDateTime(1)));
			System.out.println(String.format("DateTime.getDateTime(2) [%s]", DateTime.getDateTime(2)));
			System.out.println(String.format("DateTime.getDateTime(3) [%s]", DateTime.getDateTime(3)));
			System.out.println(String.format("DateTime.getDateTime(4) [%s]", DateTime.getDateTime(4)));
			System.out.println(String.format("DateTime.getDateTime(5) [%s]", DateTime.getDateTime(5)));
		}
		
		if (!flag) {
			/*
			 *  날짜 포멧 문자 확인
			 */
			String[] formats = {
				"yyyy/MM/dd HH:mm:ss.SSS",
				"yyMMdd HHmmss",
				"y/M/d H:m:s",
				"GG yyyyy/MM/dd ww W DDD dd F EEE aa HH kk K h mm ss SSS z Z",
			};

			for (String format : formats) {
				System.out.println(String.format("[%s] => [%s]", format, DateTime.getDateTime(format)));
			}
		}
		
		if (!flag) {
			/*
			 *  년월일이 맞는자 확인
			 */
			String[] formats = {
				"20071212",
				"20071231",
				"20071131",
				"20070231",
				"20070131",
				"20071331",
				"20071232",
			};
			
			for (String format : formats) {
				System.out.println(String.format("[%s] => [%s]", format, DateTime.checkDate(format)));
			}
		}
		
		if (!flag) {
			/*
			 *  java.util.Calender를 이용한 년월일 연산
			 */
			try {
				System.out.println("[" + DateTime.addDate("20071230", 1, 0, 0) + "]");
				System.out.println("[" + DateTime.addDate("20071230", 0, 2, 5) + "]");
				System.out.println("[" + DateTime.addDate("20071230", 0, 0, 34) + "]");
				System.out.println("[" + DateTime.addDate("20071232", 0, 0, 34) + "]");
				System.out.println();
				System.out.println("[" + DateTime.addYears("20071230", -1) + "]");
				System.out.println("[" + DateTime.addMonths("20071230", 2) + "]");
				System.out.println("[" + DateTime.addDays("20071230", 34) + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (!flag) {
			/*
			 *  두 날짜의 차이를 구한다.
			 */
			try {
				System.out.println("[" + DateTime.diffDates("20071230", "20071229") + "]");
				System.out.println("[" + DateTime.diffDates("20071229", "20071230") + "]");
				System.out.println("[" + DateTime.diffDates("20071229", "20081230") + "]");
				System.out.println("[" + DateTime.diffDates("20071229", "20071130") + "]");
				System.out.println("[" + DateTime.diffDates("20091229", "20071230") + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!flag) {

			String str = null;
			
			str = new DecimalFormat("00000").format(123);
			System.out.println(">" + str);
			
			str = new SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(new Date());
			System.out.println(">" + "123456789_YYYYMMDD_ABCDEFG".replace("YYYYMMDD", str));

			str = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(new Date(0));
			System.out.println(">" + "123456789_YYYYMMDD_ABCDEFG".replace("YYYYMMDD", str));
		}
		
		if (flag) {
			
			String key = "KEY_[HHMM]";
			String str = DateTime.getTime(5);
			String ret = key.replace("[HHMM]", str);
			
			System.out.println("[" + ret + "]");
		}
	}
	
	/**
	 * 
	 * test02
	 *
	 */
	private static void test02()
	{
		if (flag) {
			long time = 1406598843982L;
			Date date = new Date(time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
			String str = sdf.format(date);
			System.out.println("[" + str + "]");
		}
		
		if (flag) {
			long time = 1406598844983L;
			Date date = new Date(time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
			String str = sdf.format(date);
			System.out.println("[" + str + "]");
		}
		
		if (flag) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

			Date date1 = new Date(); // current
			
			long time2 = DateTime.addHours(date1, -2);
			Date date2 = new Date(time2);
			
			System.out.println("[" + sdf.format(date1) + "] [" + sdf.format(date2) + "]");
		}
	}
	
	private static void test03()
	{
		if (!flag) {
			/*
			 * long -> Date -> DateString
			 */
			long time = 1406598843982L;
			Date date = new Date(time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
			String str = sdf.format(date);
			System.out.println("[" + str + "]");
		}
		
		if (!flag) {
			/*
			 * DateString -> long
			 */
			try {
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				Date date = sdf.parse("2014-10-27 16:53:36.0");
				long time = date.getTime();
				System.out.println("[" + time + "]");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (!flag) {
			/*
			 * long -> Date -> DateString -> long
			 * 앞뒤 long 이 같은지 확인
			 */
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
			
			long time = 1406598843982L;
			if (flag) System.out.println("[" + time + "]");

			Date date = new Date(time);
			String str = sdf.format(date);
			if (flag) System.out.println("[" + str + "]");
			
			try {
				Date date2 = sdf.parse(str);
				long time2 = date2.getTime();
				if (flag) System.out.println("[" + time2 + "]");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if (flag) {
			System.out.println(">" + DateTime.getMilliSeconds("2014-10-27 16:53:36.0"));
			System.out.println(">" + DateTime.getMilliSeconds("2014/11/30 16:53:36.0"));
		}
	}
	
	private static void test04()
	{
		if (flag) {
			/*
			 * milli : 1 / 1000                   sec
			 * micro : 1 / ( 1000 * 1000 )        sec
			 * nano  : 1 / ( 1000 * 1000 * 1000 ) sec
			 */
			long before = System.nanoTime();
			
			try { Thread.sleep(10000); } catch (InterruptedException e) {}
			
			long after = System.nanoTime();
			
			System.out.println("waist time = " + (after - before) / 1000 / 1000);  // milliseconds
		}
	}
	
	private static void test05()
	{
		if (flag) {
			for (int i=0; i < 100; i++) {
				String strTime = DateTime.getTime(6);
				String[] items = strTime.split("\\s");
				
				if (flag) System.out.println("[" + strTime + "]");
				
				int secSleep = 60 - Integer.parseInt(items[1]);
				if (secSleep > 55) {
					/*
					 * 실행
					 */
					if (flag) System.out.println("\t\t 실행함. [" + items[0] + "] [" + items[1] + "]");
				}

				try { Thread.sleep(secSleep * 1000); } catch (InterruptedException e) {}
			}
		}
	}
	
	/**
	 * main entry
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (!flag) test01();
		if (!flag) test02();
		if (!flag) test03();
		if (!flag) test04();
		if (flag) test05();
	}
}
