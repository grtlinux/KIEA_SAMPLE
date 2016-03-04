public class StringVsStringBuffer {

	public static void main(String[] args) {
		String str=new String();
		StringBuffer sb=new StringBuffer();
		System.out.println(str.hashCode());
		str.concat("I").concat(" go").concat(" to shcool.");
		System.out.println(str.hashCode());
		str=str.concat("I").concat(" go").concat(" to shcool.");
		System.out.println(str.hashCode());
		System.out.println(sb.hashCode());
		sb.append("I")
		.append(" go")
		.append(" to shcool.");
		System.out.println(sb.toString());
		System.out.println(sb.hashCode());
		replaces(str);//immutable
		reverse(sb);//mutable
		System.out.println(str);
		System.out.println(sb.toString());
	}
	public static void reverse(StringBuffer sb){
		sb.reverse();
	}
	public static void replaces(String sb){
		sb.concat("GoGo");
	}
}
