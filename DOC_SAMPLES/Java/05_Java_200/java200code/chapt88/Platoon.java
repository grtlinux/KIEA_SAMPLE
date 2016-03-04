public class Platoon {//소대 
	private String name;
	public Platoon(){ //보통 4분대
		name="Platoon";
	}
	public Platoon(String name){
		this.name=name;
	}
	public String getPlatoonName(){
		return name;
	}
	public void showPlatoonName(){
		System.out.println("The "+name+" platoon.");
	}
}
