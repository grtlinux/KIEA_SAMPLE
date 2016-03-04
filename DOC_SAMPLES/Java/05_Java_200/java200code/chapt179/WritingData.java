public class WritingData {
	private String name;
	private int age;
	private double height;
	private boolean isMan;
	
	public int getAge() {   return age;  }
	public void setAge(int age) {this.age = age; }
	public double getHeight() { return height;}
	public void setHeight(double height) {this.height = height;}
	public boolean isMan() {return isMan;}
	public void setMan(boolean isMan) {this.isMan = isMan;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String toString(){
	return "이름 :"+name+", 나이:"+age+",키"+height+"cm, 남자:"+isMan;
	}
}
