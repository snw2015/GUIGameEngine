package snw.engine.main;

public class FrameThread implements Runnable
{
	private final MainFrame frame;

	public FrameThread(MainFrame frame)
	{
		this.frame = frame;
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		frame.start();
	}

}
