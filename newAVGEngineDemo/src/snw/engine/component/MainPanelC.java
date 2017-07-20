package snw.engine.component;

import javax.swing.JFrame;

import snw.math.VectorInt;

public class MainPanelC extends TopLevelComponent
{
	VectorInt mousePos = new VectorInt();

	public MainPanelC(JFrame frame)
	{
		super("mainPanel", 0, 0, 1680, 1050, frame);
		// TODO Auto-generated constructor stub
		MainMenuC mainMenu = new MainMenuC();
		add(mainMenu);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY)
	{
		print(mousePos.x + " , " + mousePos.y);
		super.mouseClicked(mousePos.x, mousePos.y);
	}

	@Override
	public boolean mouseMoved(int mouseX, int mouseY)
	{
		mousePos.x = mouseX;
		mousePos.y = mouseY;
		return (super.mouseMoved(mouseX, mouseY));
	}

}
