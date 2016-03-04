 public class AccountsMain 
 {
	public static void main( String[] args) 
	{
		Account acc1=new Account(1000); //계좌 생성
		acc1.deposit(3000);  //저축
		acc1.deposit(2000);  //저축
		acc1.withdraw(500);//출금
		System.out.println(acc1.getMoney());//현재 잔금
	}
 }
