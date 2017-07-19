package snw.engine.component;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.sun.javafx.tk.Toolkit;

import snw.engine.database.ConfigData;

public class TopLevelComponent extends FrameComponent
{

	private MovingGraphic cursor;

	public TopLevelComponent(String name, int x, int y, int width, int height,
			JFrame frame)
	{
		super(name, x, y, width, height);
		// TODO Auto-generated constructor stub
		frame.setCursor(frame.getToolkit().createCustomCursor(
				new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));

		cursor = new MovingGraphic("cursor", ConfigData.getCursorData().getImages(), 0, 0,
				50);
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		cursor.render(g);
	}

	@Override
	public boolean mouseMoved(int mouseX, int mouseY)
	{
		cursor.setPos(mouseX, mouseY);
		return (super.mouseMoved(mouseX, mouseY));
	}

}
