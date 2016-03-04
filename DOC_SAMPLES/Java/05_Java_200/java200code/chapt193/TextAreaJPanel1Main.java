public class  TextAreaJPanel1Main{
	public static void main(String[] args) {
		TextAreaJPanel1 bp=new TextAreaJPanel1();//패널-implements
		//TextAreaJPanel2 bp=new TextAreaJPanel2();//패널-this
		//TextAreaJPanel3 bp=new TextAreaJPanel3();//패널-adapter
		StartingJFrame frame=new StartingJFrame();//프레임
		frame.setMainJpanel(bp);
		frame.setSize(500,400);
		frame.validate();
	}
}
