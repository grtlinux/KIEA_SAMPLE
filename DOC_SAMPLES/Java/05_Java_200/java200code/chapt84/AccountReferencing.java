public class AccountReferencing {
 	public static void main(String[] args) {
 		Account acc1=new Account(1000); //계좌 생성
 		Account acc2=new Account(1000); //계좌 생성
 		System.out.println(acc1.hashCode());//10진수
 		System.out.println(acc1);
 		System.out.println(acc2);//type@hashCode
 		acc1.deposit(3000);  //저축
 		System.out.println(acc1);
 	}
}
