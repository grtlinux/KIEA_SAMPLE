public class  SameImageMain{
	public static void main(String[] args) {
		StartingJFrame frame=new StartingJFrame();//프레임
		SameImageJPanel bp=new SameImageJPanel(frame);//패널
		frame.setMainJpanel(bp);
		frame.validate();
	}
}