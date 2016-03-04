public class SeaAirplaneMain {

	public static void main(String[] args) {
		SeaAirplane sea =new SeaAirplane("CS110");
		Plane p=sea;
		Ship  s=sea;
		SeaAirplaneUtil.show(sea);//SeaAirplane을 아규먼트로
		SeaAirplaneUtil.show(p);//Plane을 아규먼트로
		SeaAirplaneUtil.show(s);//Ship 을 아규먼트로
		SeaAirplaneUtil.showOnly(sea);//
		SeaAirplaneUtil.showPlane(sea);
	}
}
