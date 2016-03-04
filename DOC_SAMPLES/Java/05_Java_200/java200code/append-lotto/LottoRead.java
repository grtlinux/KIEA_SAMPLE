import java.util.*;
public class LottoRead {
	public static final String ES1="<td>³¡¼ö</td>";
	public static final String ES2="<td width=\"40\">È¦Â¦</td>";
	
	public int position(String s1,String s2){
		return s1.indexOf(s2); 
	}
	public String firstString(String s1){
		StringTokenizer st=new StringTokenizer(s1.trim()," ");
		String s=st.nextToken();
		return s.trim();
	}
	
	
}
