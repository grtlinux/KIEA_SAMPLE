package com.jungbo.two;
import com.jungbo.one.*;

public class MyTopSecret extends MyMotherSecret{
	String myBoyFriend="CSharp";// default
	public MyTopSecret(int age,  String blood,
			double slushMoney,String myBoyFriend){
		super(age,blood,slushMoney);
		this.myBoyFriend=myBoyFriend;
	}
	public MyTopSecret(){
		this(28,"A",200,"Jim");
	}
	public String toString(){
		//blood를 가져올 방법이 없다. 
		return "나이: "+super.age+",비자금: "+super.getSlushMoney()+
		       ",남자친구:"+this.myBoyFriend;
	}
}
