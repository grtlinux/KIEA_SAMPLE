public class Argument1Main {

	public static void main(String[] args) {
		int myMoney=500;
		int cups=coffee(myMoney,300);
		printCoffee(cups);
		
		int noChange=400;
		coffeeIn(noChange);
		System.out.print("main: ");
		System.out.println("noChange는 "+noChange+"다.");
		
		String sChange="hyonny";
		coffeeIn(sChange);
		System.out.print("main: ");
		System.out.println("sChange는 "+sChange+"다.");
	}//main

	public static void coffeeIn(int money){
		money+=300;//money=money+300;
		money/=50;//money=money/50;
		System.out.print("method coffeeIn: ");
		System.out.println("money는 "+money+"원이다.");
		//System.out.printf("money는 %d원이다.",money);
	}//coffeeIn

	public static void coffeeIn(String money){
		money=money.replace('o','z');
		money+="안녕";
		System.out.print("method coffeeIn: ");
		System.out.println("money는 "+money+"다.");
	}//coffeeIn

	public static int coffee(int money,int coffeeValue){
		if(coffeeValue<=100){
			return -1;
		}
		int cups=-1;   //몇잔나올까 준비
		if(money > 0){
			cups=money/coffeeValue;
		}
		return cups;
	}//coffee
	
	public static int coffee(int money){
		return coffee(money,200);
	}//coffee
	
	public static void printCoffee(int cups){
		if(cups>0){
			System.out.println("커피 "+cups+"잔 입니다.");
			//System.out.printf("커피 %d잔 입니다.",cups);
		}else{
			System.out.println("액수가 모자랍니다.");
		}
	}//printCoffee
}