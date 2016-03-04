/* Copyright (c) 2008-2014, KangSeok
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG,
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package kiea.z01.ztest.t01.Image.t01;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author KangSeok
 * @date 2014. 8. 27.
 * @file_name Text2ImagTest.java
 *
 */
public class Text2ImagTest
{
	public static void test01(Canvas canvas1)
	{
		try {
			int w = canvas1.getWidth();
			int h = canvas1.getHeight();
			int type = BufferedImage.TYPE_INT_RGB;
			BufferedImage image = new BufferedImage(w, h, type);
			Graphics2D g2 = image.createGraphics();
			//canvas1.to_save = true;
			canvas1.paint(g2);
			
			try {
				ImageIO.write(image, "png", new File("C:/test.png"));
			} catch (IOException e) {}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test02(Component c1)
	{
		try {
			int w = c1.getWidth();
			int h = c1.getHeight();
			
			BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = image.createGraphics();
			c1.paint(g2);
			g2.translate(w, 0);  // this may need to be commented
			g2.dispose();
			
			try {
				ImageIO.write(image, "jpg", new File("d:/test.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		Frame myFrame = null;
		Canvas can = null;
		Graphics2D myGraphics = null;
		
		try {
			myFrame = new Frame();
			can = new Canvas();
			can.setSize(400, 200);
			myFrame.add(can);
			myFrame.setSize(400, 200);
			myFrame.setVisible(true);
			myFrame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);;
				}
			});
			
			BufferedImage buffImage = new BufferedImage(400, 200, BufferedImage.TYPE_INT_RGB);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:/text.jpg"));
			
			myGraphics = buffImage.createGraphics();
			myGraphics.setFont(new Font("Serif", Font.ITALIC, 48));
			myGraphics.setColor(Color.red);
			myGraphics.drawString("Saidul Islam", 10,  50);
			// myGraphics.drawImage(offImage, 0, 0, myFrame);
			
			/*
			buffImage.createGraphics().drawImage(buffImage, 0, 0, myFrame);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
			encoder.encode(buffImage);
			*/
			
			Toolkit tk = Toolkit.getDefaultToolkit();
			Image i = tk.getImage("D:/text.jpg");
			MediaTracker mt = new MediaTracker(myFrame);
			mt.addImage(i, 0);
			
			try {
				mt.waitForAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Graphics canGraphics = can.getGraphics();
			canGraphics.drawImage(i, 0, 0, myFrame);
			myFrame.repaint();
			
			System.out.println("gets here");
			bos.flush();
			System.out.println("flushed");
			bos.close();
			System.out.println("closed");
			buffImage.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("final");
			if (myGraphics != null) {
				myGraphics.dispose();
				System.out.println("dispose");
			}
		}
		
		System.out.println("done!");
	}
}
