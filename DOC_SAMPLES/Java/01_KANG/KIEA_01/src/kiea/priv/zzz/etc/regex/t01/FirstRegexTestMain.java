package kiea.priv.zzz.etc.regex.t01;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 
 * @author KangSeok
 *
 * Pattern regex = Pattern.compile("정규식표현", Pattern.COMMENTS|Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE|Pattern.DOTALL|Pattern.MULTILINE);
 *     Pattern.COMMENTS  :  공백무시
 *     Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE  :  대소무나 구분하지 않음.
 *     Pattern.DOTALL  :  마침표는 개행문자와 일치
 *     Pattern.MULTILINE  : 캐릿과 달러는 개행문자 위치에서 일치
 */
public class FirstRegexTestMain
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test00(String[] args)
	{
		if (flag) {
			/*
			 * 
			 */
			String string = "";

			try {
				Pattern regex = Pattern.compile("");
				Matcher regexMatcher = regex.matcher(string);
				@SuppressWarnings("unused")
				boolean foundMatch = regexMatcher.find();
			} catch (PatternSyntaxException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test01(String[] args)
	{
		if (flag) {
			/*
			 * 매치 되는지만 확인
			 */
			String string = "The regex pattern can be found..";
			
			try {
				Pattern regex = Pattern.compile("[a-z]e");
				Matcher regexMatcher = regex.matcher(string);
				if (regexMatcher.find()) {
					System.out.println("match.");
				} else {
					System.out.println("not match");
				}
				
			} catch (PatternSyntaxException e) {
				e.printStackTrace();
			}
		}
		
		if (flag) {
			/*
			 * 매치되는 문자열 출력
			 */
			String string = "The regex pattern can be found..";
			
			try {
				Pattern regex = Pattern.compile("[a-z]e");
				Matcher regexMatcher = regex.matcher(string);
				while (regexMatcher.find()) {
					System.out.println("[" + regexMatcher.group() + "]");
				}
				
			} catch (PatternSyntaxException e) {
				e.printStackTrace();
			}
		}
	}

	private static void test02(String[] args)
	{
		if (flag) {
			/*
			 * 대상 문자열과 완전히 일치되는지 검사
			 */
			String string = " The";

			try {
				Pattern regex = Pattern.compile("\\s[tT]he");
				Matcher regexMatcher = regex.matcher(string);
				if (regexMatcher.matches()) {
					System.out.println("full match.");
				} else {
					System.out.println("not full match");
				}
			} catch (PatternSyntaxException e) {
				e.printStackTrace();
			}
		}
		
		if (flag) {
			/*
			 * 일치되는 시작점과 길이를 얻는다.
			 */
			String string = "The regex pattern can be found..";
			
			try {
				Pattern regex = Pattern.compile("[pP]attern");
				Matcher regexMatcher = regex.matcher(string);
				if (regexMatcher.find()) {
					int pos = regexMatcher.start();
					int len = regexMatcher.end() - pos;
					
					System.out.println("match. [POS=" + pos + ", LEN=" + len + "]");
				} else {
					System.out.println("not match");
				}
				
			} catch (PatternSyntaxException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static void test03(String[] args)
	{
		if (flag) {
			/*
			 * 일치되는 택스트의 일부를 갖고 온다. 
			 */
			String string = "Please visit http://www.regexcookbook.com for more information";

			try {
				Pattern regex = Pattern.compile("http://([a-z0-9.-]+)");
				Matcher regexMatcher = regex.matcher(string);
				if (regexMatcher.find()) {
					System.out.println("group(1) = " + regexMatcher.group(1));
				} else {
					System.out.println("not match");
				}
			} catch (PatternSyntaxException e) {
				e.printStackTrace();
			}
		}
		
		if (flag) {
			/*
			 * 모든 일치되는 문자열을 갖고 온다.
			 */
			String string = "The lucky numbers are 7, 13, 16, 42, 65, and 99";
			
			try {
				List<String> listMatch = new ArrayList<String>();
				
				Pattern regex = Pattern.compile("\\d+");
				Matcher regexMatcher = regex.matcher(string);
				while (regexMatcher.find()) {
					System.out.println("[" + regexMatcher.group() + "]");
					listMatch.add(regexMatcher.group());
				}
				
				System.out.println(listMatch);
				
			} catch (PatternSyntaxException e) {
				e.printStackTrace();
			}
		}
		
		if (flag) {
			/*
			 * 모든 일치되는 문자열을 갖고 오고, 유효성을 검사한다.
			 */
			String string = "The lucky numbers are 7, 13, 16, 42, 65, and 99";
			
			try {
				List<String> listMatch = new ArrayList<String>();
				
				Pattern regex = Pattern.compile("\\d+");
				Matcher regexMatcher = regex.matcher(string);
				while (regexMatcher.find()) {
					System.out.println("find [" + regexMatcher.group() + "]");
					
					// 유효성검사
					if (Integer.parseInt(regexMatcher.group()) % 13 == 0)
						listMatch.add(regexMatcher.group());
				}
				
				System.out.println(listMatch);
				
			} catch (PatternSyntaxException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test04(String[] args)
	{
		if (flag) {
			/*
			 * 일치되는 문자열에서 다른 일치부를 검색 -> 비효율
			 */
			String string = "1 <b>2</b> 3 4 <b>  5 6   7</b>";

			try {
				List<String> listMatch = new ArrayList<String>();
				
				Pattern regex1 = Pattern.compile("<b>(.*?)</b>", Pattern.DOTALL);
				Pattern regex2 = Pattern.compile("\\d+");
				Matcher regexMatcher1 = regex1.matcher(string);
				while (regexMatcher1.find()) {
					Matcher regexMatcher2 = regex2.matcher(regexMatcher1.group());
					while (regexMatcher2.find()) {
						listMatch.add(regexMatcher2.group());
					}
				}
				
				System.out.println("약간 비효율>" + listMatch);
			} catch (PatternSyntaxException e) {
				e.printStackTrace();
			}
		}

		if (flag) {
			/*
			 * 일치되는 문자열에서 다른 일치부를 검색 -> 효율
			 */
			String string = "1 <b>2</b> 3 4 <b>  5 6   7</b>";

			try {
				List<String> listMatch = new ArrayList<String>();
				
				Pattern regex1 = Pattern.compile("<b>(.*?)</b>", Pattern.DOTALL);
				Pattern regex2 = Pattern.compile("\\d+");
				Matcher regexMatcher1 = regex1.matcher(string);
				Matcher regexMatcher2 = regex2.matcher(string);
				while (regexMatcher1.find()) {
					regexMatcher2.region(regexMatcher1.start(), regexMatcher1.end());
					while (regexMatcher2.find()) {
						listMatch.add(regexMatcher2.group());
					}
				}
				
				System.out.println("효율>" + listMatch);
			} catch (PatternSyntaxException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test05(String[] args)
	{
		if (flag) {
			/*
			 * 모든 일치부 치환. String 객체
			 */
			String string = "before the time before the space before the human... Before You !!!";

			try {
				String ret = string.replaceAll("before", "after");
				System.out.println(">" + ret);
			} catch (PatternSyntaxException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}

		if (flag) {
			/*
			 * 모든 일치부 치환. Matcher 객체
			 */
			String string = "before the time before the space before the human... Before You !!!";

			try {
				Pattern regex = Pattern.compile("before");
				Matcher regexMatcher = regex.matcher(string);
				try {
					String ret = regexMatcher.replaceAll("after");
					System.out.println(">" + ret);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			} catch (PatternSyntaxException e) {
				e.printStackTrace();
			}
		}
		
		if (flag) {
			/*
			 * 일부분을 재사용해서 일치부 치환. String 객체
			 */
			String string = "다음과 같습니다.  123 -> 456..";

			try {
				String ret = string.replaceAll("(\\w+) -> (\\w+)", "$2=>$1");
				System.out.println(">" + ret);
			} catch (PatternSyntaxException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}

		if (flag) {
			/*
			 * 일부부을 재사용해서 일치부 치환. Matcher 객체
			 */
			String string = "다음과 같습니다.  123 -> 456..";

			try {
				Pattern regex = Pattern.compile("(\\w+) -> (\\w+)");
				Matcher regexMatcher = regex.matcher(string);
				try {
					String ret = regexMatcher.replaceAll("$2=>$1");
					System.out.println(">" + ret);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			} catch (PatternSyntaxException e) {
				e.printStackTrace();
			}
		}
		
		if (flag) {
			/*
			 * 연산적 치환을 한다.
			 */
			String string = "다음과 같습니다.  123, 456...";

			try {
				StringBuffer sb = new StringBuffer();
				
				Pattern regex = Pattern.compile("(\\d+),\\s*(\\d+)");
				Matcher regexMatcher = regex.matcher(string);
				
				if (regexMatcher.find()) {
					Integer num1 = Integer.parseInt(regexMatcher.group(1));
					Integer num2 = Integer.parseInt(regexMatcher.group(2));
					Integer sum = num1 + num2;
					
					//regexMatcher.appendReplacement(sb, num1.toString());
					//sb.append(" + ");
					//regexMatcher.appendReplacement(sb, num2.toString());
					//sb.append(" = ");
					regexMatcher.appendReplacement(sb, sum.toString());
				}
				
				regexMatcher.appendTail(sb);

				System.out.println(">" + sb.toString());
				
			} catch (PatternSyntaxException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (!flag) test00(args);
		if (!flag) test01(args);
		if (!flag) test02(args);
		if (!flag) test03(args);
		if (!flag) test04(args);
		if (flag) test05(args);
	}
}
