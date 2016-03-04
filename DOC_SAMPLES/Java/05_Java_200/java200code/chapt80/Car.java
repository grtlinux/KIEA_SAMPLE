public class Car 
{
   //멤버필드 Field
   private int speed = 0;
   private int direction = 0;
   //생성자  Constructor
   public Car() 
   {
   }
   //메서드 Method
   public void speedUp() 
   {
     speed+=5;
   }
   public void speedDown() 
   {
     speed-=5;
   }
   public int curSpeed(){
	return speed;
   }
   public void turnDirect(int dir) 
   {
     direction+=dir;
   }
   public int curDirect(){
	return direction;
   }
}