public class  BorderFlowMain{
	public static void main(String[] args) {
		BorderFlowJPanel bp=new BorderFlowJPanel();//패널
		StartingJFrame frame=new StartingJFrame();//프레임
		frame.setMainJpanel(bp);
		frame.setSize(500,300);
		frame.validate();
		BorderFlowJPanel bp1=new BorderFlowJPanel();//패널
		StartingJFrame frame1=new StartingJFrame();//프레임
		frame1.setMainJpanel(bp1);
		frame1.setSize(300,300);
		frame1.validate();
	}
}
