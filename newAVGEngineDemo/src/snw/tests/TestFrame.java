package snw.tests;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class TestFrame extends JFrame
{
	public TestFrame()
	{
		super("test");
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		repaint();
	}

	@Override
	public void paint(Graphics g)
	{
		ImageIcon icon = new ImageIcon("file/a.png");

		Image image = icon.getImage();
		g.drawImage(image, 0, 0, this);

		BufferedImage iBuffer = new BufferedImage(200, 200,
				BufferedImage.TYPE_BYTE_GRAY);

		Graphics g2 = iBuffer.getGraphics();
		g2.drawImage(image, 0, 0, this);


		g.drawImage(iBuffer, 0, 0, this);
	}

	public static void main(String[] args)
	{
		new TestFrame();
	}
}
