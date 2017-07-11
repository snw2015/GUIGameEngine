package snw.engine.main;

public class Painter
{
	private final MainFrame frame;
	private Thread threadPaintComponent = null;

	public Painter(MainFrame frame)
	{
		this.frame = frame;
	}

	public void refresh()
	{
		if (frame.isRunning)
		{
			frame.repaint();
			if (threadPaintComponent != null)
			{
				if (!threadPaintComponent.isAlive())
				{
					threadPaintComponent = new Thread(new PaintComponent(frame));
					threadPaintComponent.setName("paintComponent");
					threadPaintComponent.start();
				}
			} else
			{
				threadPaintComponent = new Thread(new PaintComponent(frame));
				threadPaintComponent.setName("paintComponent");
				threadPaintComponent.start();

			}
		}
	}
}
