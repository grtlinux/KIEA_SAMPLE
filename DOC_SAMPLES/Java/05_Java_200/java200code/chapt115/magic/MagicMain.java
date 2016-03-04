public class MagicMain
{
	public static void main(String[] args) 
	{
		int width=9;
		Magic m=new Magic(width);
		m.make();
		int[][] magic=m.getMagic();
		
		MagicUtil.magicPrint(magic);  //마방진 출력
		MagicUtil.magicPrint(MagicUtil.magicSum(magic));//잘 만들어진 마방진?
		System.out.println(MagicUtil.isMagicSuccess(MagicUtil.magicSum(magic)));
		int [] a={3,3,3,3,3,3,3,3,3,3};
		int [] b={3,3,3,3,3,3,3,5,3,3};
		System.out.println(MagicUtil.isMagicSuccess(a));
		System.out.println(MagicUtil.isMagicSuccess(b));
		System.out.println("====================================================");
		m.reSet(5);
		m.make();
		magic=m.getMagic();
		MagicUtil.magicPrint(magic);  //마방진 출력
	}
}
