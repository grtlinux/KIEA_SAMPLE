public class WoodAnt extends Ant {
	private String where="wood";
	public void hate(){
		System.out.println(where+"새");
	}
	public void print(){
		System.out.println(getName()+"은 "+where+"에 산다.");
	}
}
