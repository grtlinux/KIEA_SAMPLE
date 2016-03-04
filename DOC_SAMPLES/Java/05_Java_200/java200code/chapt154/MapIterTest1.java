import java.util.*;
public class MapIterTest1 {

	public static void main(String[] args) {
		HashMap list=new HashMap();
		list.put("0","lee");//0
		list.clear();//모두 제거
		list.put("1","cho");//1
		list.put("2","kim");//2
		list.put("3","chung");//3
		list.put("4","min");//4
		list.put("3","jung");//3과 동일 에러
		System.out.println(list.size());//size()
		System.out.println(list.containsKey("3"));
		list.remove("2");//2제거 key를 이용
		System.out.println(list.size());
		
		print(list);//Set 만들기
	}
	public static void print(Map sets){
		Set set=sets.keySet();//key값을 Set으로
		Iterator iter=set.iterator();
		while(iter.hasNext()){
			String key=(String)iter.next();
			System.out.println(key+"  "+sets.get(key));//value
		}
	}//
}
