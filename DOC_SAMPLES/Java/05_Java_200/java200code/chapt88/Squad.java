public class Squad {//ºÐ´ë
	private String name;
	private String platoonName;
	public Squad(){
		name="Mohican";
	}
	public Squad(String name){
		this.name=name;
	}
	public Squad(Platoon platoon){
		platoonName=platoon.getPlatoonName();
	}
	public Squad(Platoon platoon, String sname){
		platoonName=platoon.getPlatoonName();
		name=sname;
	}
	public String getSquadName(){
		return name;
	}
	public void setPlatoonName(String pname){
		platoonName=pname;
	}
	public void setSquadName(String sname){
		name=sname;
	}
	public void showSquad(){
		System.out.println("The "+platoonName+" platoon, " +
				name+" squad.");
	}
}
