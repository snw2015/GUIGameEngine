package snw.engine.main;

import java.awt.event.ActionEvent;

import javax.swing.Timer;

import snw.engine.componentAVG.MainPanelC;
import snw.engine.database.Database;
import snw.engine.database.UserConfig;

public class Launcher
{
	public final static int fps = 50;
	public final static int gcInterval = 5;
	public static int counter = 0;

	public static void main(String[] args)
	{
		System.setProperty("sun.java2d.opengl","true");

		Database.loadUserData();

		MainFrame frame = new MainFrame("0.0.3");
		MainPanelC panel = new MainPanelC(frame);
		frame.setMainPanel(panel);

		Timer timerPaint = new Timer(1000 / fps, (ActionEvent e) ->
		{
			if (frame.isRunning)
			{
				frame.repaint();

				if(counter++>=gcInterval){
					System.gc();
					counter=0;
				}
			}
		});
		timerPaint.start();

		frame.start();
	}
}