package snw.engine.main;

public class PainterThread implements Runnable
{
	private final Painter painter;
	private final int fps;

	public PainterThread(Painter painter, int fps)
	{
		this.painter = painter;
		this.fps = fps;
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		while (true)
		{
			painter.refresh();

			try
			{
				Thread.sleep(1000 / fps);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
