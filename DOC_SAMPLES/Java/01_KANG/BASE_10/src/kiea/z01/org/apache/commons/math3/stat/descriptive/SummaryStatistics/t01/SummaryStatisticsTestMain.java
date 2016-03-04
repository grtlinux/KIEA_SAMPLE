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

package kiea.z01.org.apache.commons.math3.stat.descriptive.SummaryStatistics.t01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kiea.kr.co.tain.base.Flag;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

/**
 * @author KangSeok
 * @date 2014. 8. 21.
 * @file_name SummaryStatisticsTestMain.java
 *
 */
public class SummaryStatisticsTestMain
{
	private static void test01(String[] args)
	{
		double[] inputArray;
		
		if (args.length == 1) {
			/*
			 * FileName
			 */
			List<Double> list = new ArrayList<Double>();
			try {
				String str;
				BufferedReader reader = new BufferedReader(new FileReader(args[0]));
				
				while (null != (str = reader.readLine())) {
					list.add(Double.parseDouble(str));
				}
				
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			inputArray = new double[list.size()];
			int index = 0;
			for (Double d : list) {
				inputArray[index++] = d;
			}
		} else {
			if (!Flag.TRUE) {
				inputArray = new double[] {
						1, 2, 2, 4, 9, 2, 200, 4, 4, 2, 1, 2, 2, 4, 9, 2, 200, 4, 4, 2
					};
			}
			
			if (Flag.TRUE) {
				Random rand = new Random();
				int size = 8 * 1000 * 1000;     // size limit : 8,000,000.
				inputArray = new double[size];
				
				for (int i=0; i < size; i++) {
					inputArray[i] = (double) rand.nextInt(1000);
				}
			}
		}
		
		double sum   ;
		double min   ;
		double max   ;
		double q1    ;
		double q2    ;
		double q3    ;
		double mean  ;
		double var   ;
		double std   ;
		long time0, time1, time2, time3;

		time0 = System.nanoTime();
		
		// http://commons.apache.org/math/userguide/stat.html
		
		if (Flag.TRUE) {
			// Get a DescriptiveStatistics instance
			DescriptiveStatistics stats = new DescriptiveStatistics(inputArray);
			
			// Add the data from the array
			// for (int i=0; i < inputArray.length; i++) {
			//     stats.addValues(inputArray[i]);
			// }
			
			// compute some statistics
			sum   = stats.getSum();
			min   = stats.getMin();
			max   = stats.getMax();
			q1    = stats.getPercentile(25);
			q2    = stats.getPercentile(50);
			q3    = stats.getPercentile(75);
			mean  = stats.getMean();
			var   = stats.getVariance();
			std   = stats.getStandardDeviation();
			
			time1 = System.nanoTime();
			
			System.out.println("\n\n########## DescriptiveStatistics");
			
			System.out.println(String.format("count : %d", inputArray.length));
			System.out.println(String.format("sum  : %f", sum));
			System.out.println(String.format("min  : %f", min));
			System.out.println(String.format("max  : %f", max));
			System.out.println(String.format("count_unique : N/A"));
			System.out.println(String.format("q1        : %f", q1));
			System.out.println(String.format("median    : %f", q2));
			System.out.println(String.format("q3        : %f", q3));
			System.out.println(String.format("iqr       : %f", (q3 - q1)));
			System.out.println(String.format("mean      : %f", mean));
			System.out.println(String.format("variance  : %f", var));
			System.out.println(String.format("std       : %f", std));
			System.out.println(String.format("took : %d ms", (time1 - time0)/1000/1000));
		}

		if (Flag.TRUE) {
			time2 = System.nanoTime();
			
			Percentile p = new Percentile();
			q1 = p.evaluate(inputArray, 20.0);
			q2 = p.evaluate(inputArray, 50.0);
			q3 = p.evaluate(inputArray, 75.0);
			
			SummaryStatistics sStats = new SummaryStatistics();
			for (int i=0; i < inputArray.length; i++) {
				sStats.addValue(inputArray[i]);
			}
			sum = sStats.getSum();
			min = sStats.getMin();
			max = sStats.getMax();
			mean = sStats.getMean();
			var = sStats.getVariance();
			std = sStats.getStandardDeviation();
			
			time3 = System.nanoTime();

			System.out.println("\n\n########## SummaryStatistics");
			
			System.out.println(String.format("count : %d", inputArray.length));
			System.out.println(String.format("sum  : %f", sum));
			System.out.println(String.format("min  : %f", min));
			System.out.println(String.format("max  : %f", max));
			System.out.println(String.format("count_unique : N/A"));
			System.out.println(String.format("q1        : %f", q1));
			System.out.println(String.format("median    : %f", q2));
			System.out.println(String.format("q3        : %f", q3));
			System.out.println(String.format("iqr       : %f", (q3 - q1)));
			System.out.println(String.format("mean      : %f", mean));
			System.out.println(String.format("variance  : %f", var));
			System.out.println(String.format("std       : %f", std));
			System.out.println(String.format("took : %d ms", (time3 - time2)/1000/1000));
		}
	}
	
	private static void test02(String[] args)
	{
		
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01(args);
		if (Flag.TRUE) test02(args);
	}
}
