package com.ibm.jdg2e.swt.snippets.browser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Browser_137
{
	public static void main(String[] args) {
		/* Relative links: use the HTML base tag */
		String html =
			"<html><head>"
				+ "<base href=\"http://dev.eclipse.org/viewcvs/index.cgi/%7Echeckout%7E/platform-swt-home/\" >"
				+ "<title>HTML Test</title></head>"
				+ "<body><a href=\"dev.html\">local link</a></body></html>";
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		Browser browser = new Browser(shell, SWT.NONE);
		browser.setText(html);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
