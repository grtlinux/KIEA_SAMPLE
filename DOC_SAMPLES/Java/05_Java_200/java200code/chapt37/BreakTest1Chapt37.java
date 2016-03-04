public class  BreakTest1Chapt37
{
	public static void main(String[] args) 
	{
		for(int i=0;i<10;i++){
			aa:{
				System.out.println("i ==>"+i);
				for(int j=0;j<2;j++){
					if(i%5==1 || i%5==2){
						System.out.println("i :"+i+"  j:"+j);
					}else break aa;
				}// for
			}// aa:
		}// for
	}
}
