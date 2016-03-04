public class BallMain {

	public static void main(String[] args) {
		Ball b1=new Ball();
		b1.printName();
		System.out.println(b1.getName());
		BaseBall bs1=new BaseBall();
		bs1.printName();
		bs1.printSize();
		SockerBall cs1=new SockerBall();
		cs1.printName();
		cs1.printSize();
		
		Ball b2= new BaseBall();//reference 다형성
		BaseBall b3= new BaseBall();//reference 다형성
		print(b3);//argument 다형성
	}
	public static void print(Ball b){//argument 다형성
		b.printName();//overring--> method 다형성
		if(b instanceof BaseBall){//instance가 어떤것?
			BaseBall bb=(BaseBall)b;//casting
			bb.printSize();
		}else if(b instanceof SockerBall){
			SockerBall bb=(SockerBall)b;//casting
			bb.printSize();
		}
	}
}
