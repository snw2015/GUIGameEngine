package snw.engine.main;

import java.awt.Graphics;
import java.awt.Image;

public class PaintComponent implements Runnable
{
	private final MainFrame frame;

	public PaintComponent(MainFrame frame)
	{
		this.frame = frame;
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub	
		frame.getComponentGraphic();
	}
}
