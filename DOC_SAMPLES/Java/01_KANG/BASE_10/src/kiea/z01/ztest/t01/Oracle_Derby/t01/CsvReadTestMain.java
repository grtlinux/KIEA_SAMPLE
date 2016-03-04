package kiea.z01.ztest.t01.Oracle_Derby.t01;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import au.com.bytecode.opencsv.CSVReader;

public class CsvReadTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			String strPath = "D:/KANG/WORK/DERBY_TEST/anal_data/AR010141209A3120";
			String strFileName = "A24_EQP_FDC02_DETAILS01.csv";
			/*
			 * size = 19
			 * 01 LINE          VARCHAR,
			 * 02 PART          VARCHAR,
			 * 03 EQPMODEL      VARCHAR,
			 * 04 EQPID         VARCHAR,
			 * 05 MODULE_NAME   VARCHAR,
			 * 06 PROCID        VARCHAR,
			 * 07 PRODID        VARCHAR,
			 * 08 PPID          VARCHAR,
			 * 09 GLASSID       VARCHAR,
			 * 10 PROCESSSTEP   VARCHAR,
			 * 11 BEGINSTEP     VARCHAR,
			 * 12 SENSOR_NAME   VARCHAR,
			 * 13 PARAM         VARCHAR,
			 * 14 PARAM_VALUE   VARCHAR,
			 * 15 USL           VARCHAR,
			 * 16 LSL           VARCHAR,
			 * 17 BEGINTIME     VARCHAR,
			 * 18 CLUSTER_ID    VARCHAR,
			 * 19 GROUP_ID      VARCHAR,
			 */
			
			try {
				CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(strPath + File.separatorChar + strFileName), "EUC-KR"));
				String[] line;
				
				int i = 0;
				while ((line = reader.readNext()) != null) {
					
					if (i > 50)
						break;
					
					Print.println("[ %d ]", i);
					System.out.println("    01 LINE        : [" + line[ 0] + "]");
					System.out.println("    02 PART        : [" + line[ 1] + "]");
					System.out.println("    03 EQPMODEL    : [" + line[ 2] + "]");
					System.out.println("    04 EQPID       : [" + line[ 3] + "]");
					System.out.println("    05 MODULE_NAME : [" + line[ 4] + "]");
					System.out.println("    06 PROCID      : [" + line[ 5] + "]");
					System.out.println("    07 PRODID      : [" + line[ 6] + "]");
					System.out.println("    08 PPID        : [" + line[ 7] + "]");
					System.out.println("    09 GLASSID     : [" + line[ 8] + "]");
					System.out.println("    10 PROCESSSTEP : [" + line[ 9] + "]");
					System.out.println("    11 BEGINSTEP   : [" + line[10] + "]");
					System.out.println("    12 SENSOR_NAME : [" + line[11] + "]");
					System.out.println("    13 PARAM       : [" + line[12] + "]");
					System.out.println("    14 PARAM_VALUE : [" + line[13] + "]");
					System.out.println("    15 USL         : [" + line[14] + "]");
					System.out.println("    16 LSL         : [" + line[15] + "]");
					System.out.println("    17 BEGINTIME   : [" + line[16] + "]");
					System.out.println("    18 CLUSTER_ID  : [" + line[17] + "]");
					System.out.println("    19 GROUP_ID    : [" + line[18] + "]");
					System.out.println();
					
					i ++;
				}
				
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
