public class  GridTabPanesMain{
	public static void main(String[] args) {
		GridTabPanes bp=new GridTabPanes();//패널
		StartingJFrame frame=new StartingJFrame();//프레임
		frame.setMainJpanel(bp);
		frame.setSize(600,400);
		frame.validate();
	}
}
