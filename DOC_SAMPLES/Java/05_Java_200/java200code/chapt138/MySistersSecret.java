package com.jungbo.one;

public class MySistersSecret extends MyMotherSecret {
	private int weight=50;
	public MySistersSecret(int age,  String blood,
			double slushMoney,int weight){
		super(age,blood,slushMoney);
		this.weight=weight;
	}
	public MySistersSecret(){
		this(28,"A",200, 55);
	}
	public String toString(){
		return "나이: "+super.age+",비자금:"+super.getSlushMoney()+
		       ",혈액형:"+super.blood+",몸무게:"+this.weight;
	}
}
