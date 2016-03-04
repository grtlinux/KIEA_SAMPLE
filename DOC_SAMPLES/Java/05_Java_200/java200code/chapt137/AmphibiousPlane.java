public class AmphibiousPlane extends Airplane2 {

	private String landingZone="east sea 110 123";   //Âø·úÀ§Ä¡
	private int number=5;
	public AmphibiousPlane(String nameOfAirp, int fuel, int goPerL,
		             String landingZone, int number){
		super(nameOfAirp, fuel, goPerL);//ºÎ¸ð¸â¹öÃÊ±âÈ­
		this.landingZone=landingZone;
		this.number=number;
	 }
	public AmphibiousPlane(String nameOfAirp,int fuel, 
		                   String landingZone, int number){
		this(nameOfAirp,fuel,10,landingZone,number);
	}
	public AmphibiousPlane(String nameOfAirp, String landingZone){
		this(nameOfAirp,10000,10, landingZone, 5);
	}
	public AmphibiousPlane(){
		//this("C10111",10000,10, "east sea 110 123",5);
	}
	public String getLandingZone(){
		return getNameOfAirp()+"ÀÇ Âø·úÀ§Ä¡:"+landingZone;
	}
	public int getNumber(){return number;}	
	//public int getFuel() {return super.fuel;}//superÀÇ ¸â¹ö°¡ privateÀÌ¶ó X
	public String toString(){
		String s="";
		if(getFuel()>999){//getFuel()
			s=super.toString();//ºÎ¸ð Å¬·¡½º toString()
			s+="   Âø·úÀ§Ä¡´Â: "+landingZone;
			s+="   ½Â°´ÀÎ¿ø: "+number;
		}else{
			s=super.toString();
		}
		return s;
	}
}
