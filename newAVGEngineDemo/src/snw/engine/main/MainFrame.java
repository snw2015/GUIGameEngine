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
import snw.engine.componentAVG.MainPanelC;

public class MainFrame extends JFrame
{
	public boolean isRunning = true;
	public double fps = 60.0;

	private MainPanelC panel = null;
	private Image image = null;

	public MainFrame(String title)
	{
		this.setTitle(title);
		this.setBounds(0, 0, 1800, 1200);

		panel = new MainPanelC(this);

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
				panel.mouseClicked(e.getX() - 11, e.getY() - 45);

			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				panel.mousePressed(e.getX() - 11, e.getY() - 45);
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{

				panel.mouseReleased(e.getX() - 11, e.getY() - 45);
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
				panel.mouseMoved(e.getX() - 11, e.getY() - 45);
			}

			@Override
			public void mouseDragged(MouseEvent e)
			{
				panel.mouseDragged(e.getX() - 11, e.getY() - 45);
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
			g.drawImage(image, 11, 45, this);
		}
	}

	public void getComponentGraphic()
	{
		panel.render(image.getGraphics());
		image.flush();
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
