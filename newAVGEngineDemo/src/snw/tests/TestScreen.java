package snw.tests;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class TestScreen extends JFrame
{
	public TestScreen()
	{
		System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
		System.exit(0);
	}

	public static void main(String[] args)
	{
		new TestScreen();
	}
}
