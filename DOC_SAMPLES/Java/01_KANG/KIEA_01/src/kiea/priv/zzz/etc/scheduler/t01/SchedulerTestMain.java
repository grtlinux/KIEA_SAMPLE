package kiea.priv.zzz.etc.scheduler.t01;

import java.util.SortedSet;
import java.util.TreeSet;

public class SchedulerTestMain
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		if (flag) {
			/*
			 * 1. 환경설정
			 */
		}
		
		if (flag) {
			/*
			 * 2. 최초작업
			 */
		}
		
		if (flag) {
			/*
			 * 3. 처리작업
			 */
		}
		
		if (flag) {
			/*
			 * 4. 종료작업
			 */
		}
	}
	
	private static void test02(String[] args)
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
	
	private static void test03(String[] args) throws Exception
	{
		if (flag) {
			try {
				/*
				 * schedule parsing
				 */
				String strSched = " 1:5   ; 2 : 10,  11 , 20 \t  \f  ; 3 - 5 , 4 -  9  : 30 -40    ;  20 - 18  : 0 / 10  ";
				strSched = " 01:5   ; 2 : 10,  11 , 20 \t  \f  ; 3 - 5 , 4 -  9, 23  : 30 -40    ;; 26 - 22  : 0 / 7, 1-3, 11";
				strSched = "0-23 : 2 / 10";
				//strSched = "0-23 :  3 / 5";
				
				SortedSet<String> setHHMM = new TreeSet<String>();

				// 1. 공백 제거
				strSched = strSched.replaceAll("\\s", "");
				if (flag) System.out.println("[" + strSched + "]");
				
				// 2. ';'로 분리
				String[] schs = strSched.split(";");
				if (flag) {
					// 확인출력
					for (String sch : schs) {
						System.out.println("각 구분>" + sch);
					}
				}
				
				// 3. 각 항목을 HH:MM 형태로 만든다.
				for (String sch : schs) {
					String[] hhmm = sch.split(":");
					if (hhmm.length != 2)
						continue;
					
					if ("".equals(hhmm[0]) || "".equals(hhmm[1]))
						continue;
					
					SortedSet<String> setHH = new TreeSet<String>();
					SortedSet<String> setMM = new TreeSet<String>();
					
					if (flag) {
						/*
						 * 시 처리
						 */
						String[] hhmm2 = null;
						if (hhmm[0].indexOf(',') >= 0) {
							// ',' 처리
							hhmm2 = hhmm[0].split(",");
						} else {
							hhmm2 = new String[] { hhmm[0] };
						}
						
						for (String str : hhmm2) {
							if (str.indexOf('-') >= 0) {
								String[] items = str.split("-");
								int min = Math.min(Integer.parseInt(items[0]), Integer.parseInt(items[1]));
								int max = Math.max(Integer.parseInt(items[0]), Integer.parseInt(items[1]));
								for (int i=min; i < 24 && i <= max; i++) {
									setHH.add(String.format("%02d", i));
								}
							} else if (str.indexOf('/') >= 0) {
								String[] items = str.split("/");
								for (int hour = Integer.parseInt(items[0]); hour < 24; hour += Integer.parseInt(items[1])) {
									setHH.add(String.format("%02d", hour));
								}
							} else {
								setHH.add(String.format("%02d", Integer.parseInt(str)));
							}
						}
						
						if (!flag) {
							// 확인출력
							for (String str : setHH) {
								System.out.println("setHH >" + str);
							}
						}
					}
					
					if (flag) {
						/*
						 * 분 처리
						 */
						String[] hhmm2 = null;
						if (hhmm[1].indexOf(',') >= 0) {
							// ',' 처리
							hhmm2 = hhmm[1].split(",");
						} else {
							hhmm2 = new String[] { hhmm[1] };
						}
						
						for (String str : hhmm2) {
							if (str.indexOf('-') >= 0) {
								String[] items = str.split("-");
								int min = Math.min(Integer.parseInt(items[0]), Integer.parseInt(items[1]));
								int max = Math.max(Integer.parseInt(items[0]), Integer.parseInt(items[1]));
								for (int i=min; i < 60 && i <= max; i++) {
									setMM.add(String.format("%02d", i));
								}
							} else if (str.indexOf('/') >= 0) {
								String[] items = str.split("/");
								for (int min = Integer.parseInt(items[0]); min < 60; min += Integer.parseInt(items[1])) {
									setMM.add(String.format("%02d", min));
								}
							} else {
								setMM.add(String.format("%02d", Integer.parseInt(str)));
							}
						}
						
						if (!flag) {
							// 확인출력
							for (String str : setMM) {
								System.out.println("setMM >" + str);
							}
						}
					}
					
					if (flag) {
						/*
						 * 시:분 생성
						 */
						for (String hh : setHH) {
							for (String mm : setMM) {
								if (!flag) System.out.println(String.format("%s:%s", hh, mm));
								
								setHHMM.add(String.format("%s:%s", hh, mm));
							}
						}
					}
					
					if (!flag) System.out.println();
				}
				
				if (flag) {
					// 확인출력
					int i=0;
					for (String str : setHHMM) {
						System.out.println(String.format("%3d [%s]", ++i, str));
					}
				}
			} catch (Exception e) {
				// e.printStackTrace();
				throw e;
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (!flag) test01(args);
		if (!flag) test02(args);
		if (flag) test03(args);
	}
}
