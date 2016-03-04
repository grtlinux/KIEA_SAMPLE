public class DivisorMain
{
	public static void main(String[] args) 
	{
		System.out.println("5가 10의 약수? "+Divisor.isMod(10,5));
		System.out.println("5가 13의 약수? "+Divisor.isMod(13,5));
		Divisor.printDivisor(15); //15의 모든 약수 1 포함
	}
}
