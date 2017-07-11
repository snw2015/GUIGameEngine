package snw.engine.main;

public class Launcher
{
	public final static int fps = 50;

	public static void main(String[] args)
	{
		MainFrame frame = new MainFrame();
		Thread frameThread = new Thread(new FrameThread(frame));
		frameThread.setName("frame");
		frameThread.start();

		Painter painter = new Painter(frame);
		Thread paintThread = new Thread(new PainterThread(painter, fps));
		paintThread.setName("paint");
		paintThread.start();
	}
}