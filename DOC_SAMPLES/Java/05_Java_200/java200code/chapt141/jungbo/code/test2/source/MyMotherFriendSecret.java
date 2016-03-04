package com.jungbo.one;

public class MyMotherFriendSecret {
	private String address="종로구";
	public MyMotherFriendSecret(String address){
		this.address=address;
	}
	public MyMotherFriendSecret(){
		this("성진구");
	}
	public String getAddress(){
		return address;
	}
	public void seeMyMother(MyMotherSecret mym){
		StringBuffer sb=new StringBuffer();
		sb.append("우리엄마나이:"+mym.age+",");//protected
		sb.append("우리엄마혈액형:"+mym.blood+",");//default
		sb.append("우리엄마비자금:"+mym.getSlushMoney());//
		System.out.println(sb.toString());
	}
}
