package com.ibm.jdg2e.swt.snippets.display;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Display_16
{
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		final int time = 500;
		Runnable timer = new Runnable() {
			public void run() {
				Point point = display.getCursorLocation();
				Rectangle rect = shell.getBounds();
				if (rect.contains(point)) {
					System.out.println("In");
				} else {
					System.out.println("Out");
				}
				display.timerExec(time, this);
			}
		};
		display.timerExec(time, timer);
		shell.setSize(200, 200);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
