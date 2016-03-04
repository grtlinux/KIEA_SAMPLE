package com.ibm.jdg2e.swt.snippets.display;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Display_60
{
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(200, 200);
		shell.open();
		display.timerExec(5000, new Runnable() {
			public void run() {
				System.out.println("5000");
			}
		});
		display.timerExec(2000, new Runnable() {
			public void run() {
				System.out.println("2000");
			}
		});
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
