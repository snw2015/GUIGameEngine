package snw.engine.main;

import java.awt.event.ActionEvent;

import javax.swing.Timer;

import snw.engine.database.Database;
import snw.engine.database.UserConfig;

public class Launcher
{
	public final static int fps = 50;

	public static void main(String[] args)
	{
		Database.loadUserData();

		MainFrame frame = new MainFrame("0.0.3");
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