package snw.engine.main;

import java.awt.event.ActionEvent;

import javax.swing.Timer;

public class Launcher
{
	public final static int fps = 50;

	public static void main(String[] args)
	{
		MainFrame frame = new MainFrame();
		Timer timerFrame = new Timer(1000 / fps, (ActionEvent e) ->
		{
			frame.start();
		});
		timerFrame.start();

		Timer timerPaint = new Timer(1000 / fps, (ActionEvent e) ->
		{
			if (frame.isRunning)
			{
				frame.repaint();
				frame.getComponentGraphic();
				System.gc();
			}
		});
		timerPaint.start();
	}
}