package kiea.priv.zzz.book.JavaDesignPattern.pattern.P22_Command.t01;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TestMain extends JFrame implements ActionListener, MouseMotionListener, WindowListener
{
	private static final long serialVersionUID = 1L;

	private MacroCommand history = new MacroCommand();
	
	private DrawCanvas canvas = new DrawCanvas(400, 400, history);
	
	private JButton clearButton = new JButton("clear");
	
	public TestMain(String title)
	{
		super(title);
		
		this.addWindowListener(this);
		canvas.addMouseMotionListener(this);
		clearButton.addActionListener(this);
		
		Box buttonBox = new Box(BoxLayout.X_AXIS);
		buttonBox.add(clearButton);
		Box mainBox = new Box(BoxLayout.Y_AXIS);
		mainBox.add(buttonBox);
		mainBox.add(canvas);
		getContentPane().add(mainBox);
		
		pack();
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == clearButton) {
			history.clear();
			canvas.repaint();
		}
	}
	
	public void mouseMoved(MouseEvent e)
	{
	}
	
	public void mouseDragged(MouseEvent e)
	{
		Command cmd = new DrawCommand(canvas, e.getPoint());
		history.append(cmd);
		cmd.execute();
	}
	
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
	
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	
	public static void main(String[] args)
	{
		new TestMain("Command Pattern Sample");
	}
}
