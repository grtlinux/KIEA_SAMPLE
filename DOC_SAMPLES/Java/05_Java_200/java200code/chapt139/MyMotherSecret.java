package com.jungbo.one;
public class MyMotherSecret {
	protected int age=35;
	          String blood="A";//defalut
	private   double slushMoney=300;//비자금
	public MyMotherSecret(int age, String blood,double slushMoney){
		this.age=age;
		this.blood=blood;
		this.slushMoney=slushMoney;
	}
	public MyMotherSecret(){
		this(35,"A",300);
	}
	public double getSlushMoney(){
		return slushMoney;
	}
	public String toString(){
		return "나이:"+age+",혈액형: "+blood+",비자금:"+slushMoney;
	}
}
