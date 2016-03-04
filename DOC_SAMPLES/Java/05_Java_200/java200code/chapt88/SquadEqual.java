public class  SquadEqual
{
	public static void main(String[] args) 
	{
		Squad squ1=new Squad("Batal");
		Squad squ2=new Squad("Batal");
		Squad squ3=new Squad("Tier");
		Squad squ4=squ1; //shallow copy

		System.out.printf("squ1==squ2 : %b%n",squ1==squ2);
		System.out.printf("squ1.equals(squ2) : %b%n",squ1.equals(squ2));
		System.out.printf("squ1==squ3 : %b%n",squ1==squ3);
		System.out.printf("squ1.equals(squ3) : %b%n",squ1.equals(squ3));
		System.out.printf("squ1==squ4 : %b%n",squ1==squ4);
		System.out.printf("squ1.equals(squ4) : %b%n",squ1==squ4);
	}
}
