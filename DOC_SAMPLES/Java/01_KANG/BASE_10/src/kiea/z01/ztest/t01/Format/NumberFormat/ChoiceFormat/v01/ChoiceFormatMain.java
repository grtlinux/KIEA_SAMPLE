package kiea.z01.ztest.t01.Format.NumberFormat.ChoiceFormat.v01;

import java.text.ChoiceFormat;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;

public class ChoiceFormatMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			/*
			 * ASCENDING
			 */
			double[] limits = { 60, 70, 80, 90 };
			String[] grades = { "D", "C", "B", "A" };
			
			int[] scores = { 100, 96, 89, 70, 52, 60, 70 };
			ChoiceFormat choiceFormat = new ChoiceFormat(limits, grades);
			
			for (int score : scores) {
				Print.println("%d -> %s", score, choiceFormat.format(score));
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * DECENDING -> 결과가 이상할 것임.
			 */
			double[] limits = { 90, 80, 70, 60 };
			String[] grades = { "a", "b", "c", "d" };
			
			int[] scores = { 100, 96, 89, 70, 52, 60, 70 };
			ChoiceFormat choiceFormat = new ChoiceFormat(limits, grades);
			
			for (int score : scores) {
				Print.println("%d -> %s", score, choiceFormat.format(score));
			}
		}
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			/*
			 * 
			 */
			String pattern = "0#불합격|60#턱걸이|70<무사통과|80#잘했네|90#우수함";
			int[] scores = { 100, 95, 88, 80, 52, 60, 70, 71, 97, 63, 45, 88 };
			
			ChoiceFormat choiceFormat = new ChoiceFormat(pattern);
			
			for (int score : scores) {
				Print.println("%d -> %s", score, choiceFormat.format(score));
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();
	}
}
