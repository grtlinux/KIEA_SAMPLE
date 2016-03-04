package com.ibm.jdg2e.swt.snippets.display;

import org.eclipse.swt.widgets.Display;

public class Display_42
{
	public static void main(String[] args) {
		Display display = new Display();
		System.out.println(
			"BOUNDS="
				+ display.getBounds()
				+ " CLIENT="
				+ display.getClientArea());
		display.dispose();
	}
}
