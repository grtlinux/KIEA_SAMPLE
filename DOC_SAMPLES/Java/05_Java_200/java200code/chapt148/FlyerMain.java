public class FlyerMain {

	public static void main(String[] args) {
		System.out.println(Flyer.fast);//»ó¼ö
		
		Bird b=new Bird();
		FlyerUtil.show(b);
		Airplane ap=new Airplane();
		ap.fly();
		FlyerUtil.show(ap);
		Flyer f=new Bird();
		f.fly();
		System.out.println(f.isAnimal());
		FlyerUtil.show(f);
		Bird bf=(Bird)f;
		FlyerUtil.show(bf);
	}
}
