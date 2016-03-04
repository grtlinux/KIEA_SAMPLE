import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public abstract class AbstractJFrame extends JFrame{
        private JPanel mainp=null;
        public AbstractJFrame(String title){
            super(title);//-----Å¸ÀÌÆ²-----//
            mainp = (JPanel) this.getContentPane();
            mainp.setLayout(new BorderLayout());
            inits( );
            initFrame();
            addListeners();
        }
        public void initFrame(){
            System.out.println(this.getClass().getName()+" Start!!");
            Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension frameSize = this.getSize();
            if (frameSize.height > monitorSize.height) {
                frameSize.height = monitorSize.height;
              }
            if (frameSize.width > monitorSize.width) {
              frameSize.width = monitorSize.width;
            }
            int locationX=(monitorSize.width - frameSize.width) / 2;
            int locationY=(monitorSize.height - frameSize.height) / 2;
            this.setLocation(locationX, locationY);
            this.setVisible(true);
			enableEvents(AWTEvent.WINDOW_EVENT_MASK);//
        }
        public abstract void addListeners();
        public abstract void inits();
		public void processWindowEvent(WindowEvent e) {
			super.processWindowEvent(e);
			if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			  this.dispose();
			  System.out.println(this.getClass().getName()+" End!!");
			  System.exit(1);
			}
		}
        public void setMainJpanel(javax.swing.JComponent c){
          mainp.add(c);
        }
}
