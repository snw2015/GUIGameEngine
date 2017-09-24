package snw.tests;

import snw.file.FileDirectReader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.*;

public class TestScreen extends JFrame
{
	Image graphicTest = FileDirectReader.getImage("file/image/background_main_menu.png");

	public TestScreen()
	{
		System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
		this.setSize(200,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		System.out.println(graphicTest.getHeight(null));
		while(true)
		{
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paint(Graphics g)
	{
		BufferedImage iBuffer = new BufferedImage(graphicTest.getWidth(null),graphicTest.getHeight(null),BufferedImage.TYPE_4BYTE_ABGR);
		iBuffer.getGraphics().drawImage(graphicTest,0,0,this);
		g.drawImage(iBuffer,0,0,this);

		iBuffer.getGraphics().dispose();
		iBuffer.flush();
		iBuffer = null;
	}

	public static void main(String[] args)
	{
		new TestScreen();
	}

}
