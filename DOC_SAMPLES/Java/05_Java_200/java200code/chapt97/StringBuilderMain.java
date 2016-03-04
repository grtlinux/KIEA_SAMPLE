public class StringBuilderMain {
	public static void main(String[] args) {
		//synchronized ¾ÈÇÑ StringBuffer
		StringBuilder sbu=new StringBuilder();
		sbu.append("I")
		.append(" go")
		.append(" to the shcool.");
		System.out.println(sbu);
		sbu.replace(7,11,"");
		System.out.println(sbu);
		sbu.reverse();
		System.out.println(sbu);
		sbu.deleteCharAt(3);
		System.out.println(sbu);
		sbu.delete(1,3);
		System.out.println(sbu);
		sbu.substring(0);
		System.out.println(sbu);
		sbu.substring(0,4);
		System.out.println(sbu);
	}
}
