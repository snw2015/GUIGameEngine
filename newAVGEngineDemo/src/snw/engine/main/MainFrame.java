package snw.engine.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import snw.engine.component.MainPanel;

public class MainFrame extends JFrame
{
	public boolean isRunning = true;
	public double fps = 60.0;

	private MainPanel panel = null;
	private Image image = null;

	private Thread threadPaint = null;
	private Thread threadProcess = null;
	private Painter painter = new Painter(this);

	public MainFrame()
	{
		this.setTitle("0.0.1");
		this.setBounds(0, 0, 1400, 900);

		panel = new MainPanel("MainPanel", 0, 0, getWidth(), getHeight());

		this.addKeyListener(new KeyListener()
		{

			@Override
			public void keyTyped(KeyEvent e)
			{
				panel.keyTyped(e.getKeyChar());
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				panel.keyPressed(e.getKeyCode());
			}

			@Override
			public void keyReleased(KeyEvent e)
			{

				panel.keyReleased(e.getKeyCode());
			}
		});
		this.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				panel.mouseClicked(e.getX(), e.getY());

			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				panel.mousePressed(e.getX(), e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{

				panel.mouseReleased(e.getX(), e.getY());
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				panel.mouseEntered();
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				panel.mouseExited();
			}
		});
		this.addMouseMotionListener(new MouseMotionListener()
		{

			@Override
			public void mouseMoved(MouseEvent e)
			{

				panel.mouseMoved(e.getX(), e.getY());

			}

			@Override
			public void mouseDragged(MouseEvent e)
			{
				panel.mouseDragged(e.getX(), e.getY());
			}
		});

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		image = createImage(this.getWidth(), this.getHeight());

	}

	public void paint(Graphics g)
	{
		if (image != null)
		{
			g.drawImage(image, 11, 43, this);
		}
	}

	public void getComponentGraphic()
	{
		// TODO Auto-generated method stub
		Image imageBuffer = createImage(this.getWidth(), this.getHeight());
		Graphics g = imageBuffer.getGraphics();
		panel.render(g);
		this.image = imageBuffer;
	}

	public static void print(String s)
	{
		System.out.println(s);
	}

	public void start()
	{
		// TODO Auto-generated method stub

	}
}
