import javax.swing.JOptionPane;
public class OptionPaneInput {

	public static void main(String[] args) {
		
		String str  =
		  JOptionPane.showInputDialog("출력하고 싶은 내용을 넣으세요");
		JOptionPane.showMessageDialog( null , str);//dialogbox
		
		int yesOrNo=
			JOptionPane.showConfirmDialog(  null,"choose one", 
				   "Title: choose one", JOptionPane.YES_NO_OPTION);         
		                                                                            
		 if(yesOrNo==JOptionPane.YES_OPTION ){
		     System.out.println("You choose YES_OPTION option");
		 }else {
		     System.out.println("You choose NO_OPTION option");
		 }
		int inform=JOptionPane.showConfirmDialog(  null,"OK or Cancel", 
				   "Title: choose one", JOptionPane.OK_CANCEL_OPTION);    
		int inform1=JOptionPane.showConfirmDialog(  null,"Yes, No, Cancel", 
				   "Title: choose one", JOptionPane.YES_NO_CANCEL_OPTION);
		
		System.exit(1); //console에서 gui를 사용하기 때문
	}
}
