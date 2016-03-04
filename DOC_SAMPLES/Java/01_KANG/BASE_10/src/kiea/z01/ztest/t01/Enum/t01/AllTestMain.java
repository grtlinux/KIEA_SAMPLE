package kiea.z01.ztest.t01.Enum.t01;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;

enum Type01   // protected
{
	WALKING,   // walking  0
	RUNNING,   // running  1
	TRACKING,  // tracking 2
	HIKING,    // hiking   3
	;
}

class Bean01
{
	private String name;
	private int size;
	private Type01 type;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getSize()
	{
		return size;
	}
	public void setSize(int size)
	{
		this.size = size;
	}
	public Type01 getType()
	{
		return type;
	}
	public void setType(Type01 type)
	{
		this.type = type;
	}
}

class Bean02
{
	static enum Type02   // protected
	{
		WALKING, RUNNING, TRACKING, HIKING,;
	}
	private String name;
	private int size;
	private Type02 type;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getSize()
	{
		return size;
	}
	public void setSize(int size)
	{
		this.size = size;
	}
	public Type02 getType()
	{
		return type;
	}
	public void setType(Type02 type)
	{
		this.type = type;
	}
}

enum Type04 {
	WALKING("워킹화"),     // 0
	RUNNING("러닝화"),     // 1
	TRACKING("트래킹화"),  // 2
	HIKING("등산화"),      // 3
	;
	
	private final String name;
	
	private Type04(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
}

public class AllTestMain
{

	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) Print.println("------------- %s ---------------", "test01");
		
		Bean01 main = new Bean01();
		
		main.setName("나이키");
		main.setSize(230);
		main.setType(Type01.RUNNING);
		
		System.out.println("신발이름   : " + main.getName());
		System.out.println("신발사이즈 : " + main.getSize());
		System.out.println("신발종류   : " + main.getType());
		
		System.out.println();
		for (Type01 type : Type01.values()) {
			System.out.println(type + ":" + type.ordinal());
		}
		
		Type01 tp1 = Type01.WALKING;
		Type01 tp2 = Type01.valueOf("WALKING");
		
		System.out.println(tp1 + ", " + tp2);
	}
	
	private static void test02()
	{
		if (Flag.TRUE) Print.println("------------- %s ---------------", "test02");
		
		Bean02 main = new Bean02();
		
		main.setName("나이키");
		main.setSize(230);
		main.setType(Bean02.Type02.RUNNING);
		
		System.out.println("신발이름   : " + main.getName());
		System.out.println("신발사이즈 : " + main.getSize());
		System.out.println("신발종류   : " + main.getType());
		
		System.out.println();
		for (Bean02.Type02 type : Bean02.Type02.values()) {
			System.out.println(type + ":" + type.ordinal());
		}
		
		Bean02.Type02 tp1 = Bean02.Type02.WALKING;
		Bean02.Type02 tp2 = Bean02.Type02.valueOf("WALKING");
		
		System.out.println(tp1 + ", " + tp2);
	}
	
	private static void test04()
	{
		if (Flag.TRUE) Print.println("------------- %s ---------------", "test04");

		for (Type04 type : Type04.values()) {
			System.out.println(type + ":" + type.ordinal() + ":" + type.getName());
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
		if (Flag.TRUE) test02();
		if (Flag.TRUE) test04();
	}
}
