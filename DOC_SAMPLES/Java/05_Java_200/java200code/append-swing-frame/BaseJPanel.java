import javax.swing.*;
public abstract class BaseJPanel extends JPanel {
	public BaseJPanel() {
		super();
		System.out.println("BasePanel -----1");
	}
	public abstract void addListeners();
	public abstract void inits();
	public void starts(){
		inits();
		addListeners();
	}
}