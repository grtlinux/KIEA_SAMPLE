package kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.A3_1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.A3_1.activeobject.ActiveObject;
import kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.A3_1.activeobject.ActiveObjectFactory;
import kiea.priv.zzz.book.JavaDesignPattern.thread.T12_ActiveObject.t01.A3_1.activeobject.Result;

public class MyFrame extends JFrame implements ActionListener {
    private final JTextField textfield = new JTextField("word", 10);
    private final JButton button = new JButton("Search");
    private final JTextArea textarea = new JTextArea(20, 30);
    private final ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
    private final static String NEWLINE = System.getProperty("line.separator");

    public MyFrame() {
        super("ActiveObject Sample");
        getContentPane().setLayout(new BorderLayout());

        // North
        JPanel north = new JPanel();
        north.add(new JLabel("Search:"));
        north.add(textfield);
        north.add(button);
        button.addActionListener(this);

        // Center
        JScrollPane center = new JScrollPane(textarea);

        // Layout
        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // Searc
    public void actionPerformed(ActionEvent e) {
        searchWord(textfield.getText());
    }

    // 
    private void println(String line) {
        textarea.append(line + NEWLINE);
    }

    // 
    private void searchWord(final String word) { 
        // 
        final Result result = activeObject.search(word);
        println("Searching " + word + "...");
        // 
        new Thread() {
            public void run() {
                // 
                final String url = (String)result.getResultValue();
                // 
                SwingUtilities.invokeLater(
                    new Runnable() {
                        public void run() {
                            MyFrame.this.println("word = " + word + ", URL = " + url);
                        }
                    }
                );
            }
        }.start();
    }
}
