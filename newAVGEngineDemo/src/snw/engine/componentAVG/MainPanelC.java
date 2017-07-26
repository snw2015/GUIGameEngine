package snw.engine.componentAVG;

import javax.swing.JFrame;

import snw.engine.component.TopLevelComponent;
import snw.math.VectorInt;

public class MainPanelC extends TopLevelComponent
{
	VectorInt mousePosition = new VectorInt();

	public MainPanelC(JFrame frame)
	{
		super("mainPanel", 0, 0, 1680, 1050, frame);
		// TODO Auto-generated constructor stub
		startMainMenu();
	}

	public void startMainScreen()
	{
		MainGameScreenC mainScreen = new MainGameScreenC();
		mainScreen.preProcess();
		mainScreen.setReaction((command) ->
		{
			switch (command)
			{
			case MainGameScreenC.COMMAND_END:
				mainScreen.exit();
				remove(mainScreen);
				startMainMenu();
				break;
			case MainGameScreenC.COMMAND_SAVE:
				break;
			default:
				break;
			}
		});
		add(mainScreen);
	}

	private void startMainMenu()
	{
		// TODO Auto-generated method stub
		MainMenuC mainMenu = new MainMenuC();
		mainMenu.preProcess();
		add(mainMenu);

		mainMenu.setReaction((int command) ->
		{
			switch (command)
			{
			case MainMenuC.COMMAND_START:
				mainMenu.exit();
				remove(mainMenu);
				startMainScreen();
				break;
			case MainMenuC.COMMAND_CONTINUE:
				break;
			case MainMenuC.COMMAND_EXIT:
				System.exit(0);
				break;
			default:
				break;
			}
		});
	}

	@Override
	public void update()
	{
		super.mouseMoved(mousePosition.x, mousePosition.y);
		super.update();
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY)
	{
		print("Clicked at: " + mousePosition.x + " , " + mousePosition.y);
		super.mouseClicked(mousePosition.x, mousePosition.y);
	}

	@Override
	public boolean mouseMoved(int mouseX, int mouseY)
	{
		mousePosition.x = mouseX;
		mousePosition.y = mouseY;
		return (super.mouseMoved(mouseX, mouseY));
	}

}
