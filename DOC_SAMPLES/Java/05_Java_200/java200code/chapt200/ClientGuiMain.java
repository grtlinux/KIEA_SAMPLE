public class  ClientGuiMain{
	public static void main(String[] args) {
		StartingJFrame frame=new StartingJFrame();//프레임
		ClientGui bp=new ClientGui(frame, "127.0.0.1", 5420);//패널
		bp.giveAndTake();
		frame.setMainJpanel(bp);
		frame.setSize(550,400);
		frame.validate();
	}
}