/*
 * Copyright (c) 2004 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 3nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose,
 * including teaching and use in open-source projects.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book, 
 * please visit http://www.davidflanagan.com/javaexamples3.
 */
package kiea.priv.zzz.book.Nutshell.t01.gui;
import java.awt.*;
import javax.swing.*;

public class FlowLayoutPane extends JPanel {
    public FlowLayoutPane() {
	// Use a FlowLayout layout manager.  Left justify rows.
	// Leave 10 pixels of horizontal and vertical space between components.
	this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

	// Add some buttons to demonstrate the layout.
	String spaces = "";  // Used to make the buttons different
	for(int i = 1; i <= 9; i++) {
	    this.add(new JButton("Button #" + i + spaces));
	    spaces += " ";
	}

	// Give ourselves a default size
	this.setPreferredSize(new Dimension(500, 200));
    }
}
