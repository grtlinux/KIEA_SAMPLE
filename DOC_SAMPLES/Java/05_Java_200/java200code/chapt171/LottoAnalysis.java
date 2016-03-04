import java.util.*;
public class LottoAnalysis {
	public String[] getLottos(String s){
		String [] str=new String[10];
		StringTokenizer st=new java.util.StringTokenizer(s); 
		for(int i=0;i<str.length;i++){
			str[i]=st.nextToken();//10개의 단어를 받는다.
		}
		return str;
	}
	public int to10(String s){
		String ss=s;
		if(s.charAt(0)=='0'){  //첫번 째 문자가 0인가?
			ss=s.replace('0',' '); //0을 공백으로 변화 시켜라 
		}
		return Integer.parseInt(ss.trim());  //공백 제거후 int로 변환
	}
	public int[] toInt(String s){
		int [] aa=new int[6];//
		StringTokenizer st=new java.util.StringTokenizer(s);
		st.nextToken();//
		//구분시킨 스트링을 숫자로 바꾸어서 배열에 저장
		for(int i=0;i<aa.length;i++){
			aa[i]=to10(st.nextToken().trim());
		}
		return aa;
	}
}
