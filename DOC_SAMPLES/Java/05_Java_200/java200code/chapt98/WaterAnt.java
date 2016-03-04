public class WaterAnt extends Ant {
	private String where="water";
	public void show(){
		System.out.println(where+"주변");
	}
	public void print(){
		System.out.println(getName()+"은 "+where+"에 산다.");
	}
}
