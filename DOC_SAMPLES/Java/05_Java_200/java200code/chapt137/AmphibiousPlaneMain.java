public class  AmphibiousPlaneMain
{
	public static void main(String[] args) 
	{
		AmphibiousPlane amphi=new AmphibiousPlane("Sea Hawk",6000,"East sea 123", 8);
		AmphibiousPlane amphi2=new AmphibiousPlane();
		System.out.println(amphi);
		System.out.println(amphi2);
		System.out.println(amphi2.getLandingZone());
	}
}
