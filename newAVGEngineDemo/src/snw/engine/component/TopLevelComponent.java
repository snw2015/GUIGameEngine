package snw.engine.component;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.sun.javafx.tk.Toolkit;

import snw.engine.database.Database;
import snw.math.VectorInt;

public class TopLevelComponent extends FrameComponent
{

	private MovingGraphic cursor;

	public TopLevelComponent(String name, int x, int y, int width, int height,
			JFrame frame)
	{
		super(name, x, y, width, height,true);
		// TODO Auto-generated constructor stub
		frame.setCursor(frame.getToolkit().createCustomCursor(
				new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));

		cursor = new MovingGraphic("cursor", Database.getCursorData().getImages(), 0, 0,
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

	@Override
	public void mouseDragged(int mouseX, int mouseY)
	{
		cursor.setPos(mouseX, mouseY);
		setCursor("drag");
		super.mouseDragged(mouseX, mouseY);
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY)
	{
		setCursor("normal");
		super.mouseReleased(mouseX, mouseY);
	}

	protected void setCursor(String typeName)
	{
		int posX = cursor.getX();
		int posY = cursor.getY();
		cursor = null;
		cursor = new MovingGraphic("cursor", Database.getCursorData().getImages(typeName),
				posX, posY, 50);
	}
}