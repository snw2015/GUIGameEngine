package snw.engine.componentAVG;

import javax.swing.JFrame;

import snw.engine.component.TopLevelComponent;
import snw.math.VectorInt;

public class MainPanelC extends TopLevelComponent
{
	boolean isDragging = false;

	public MainPanelC()
	{
		super("mainPanel", 0, 0, 1680, 1050,true);
		// TODO Auto-generated constructor stub
	}

	private void startMainScreen()
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
		refocusMouse();
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

		refocusMouse();
	}

	@Override
	public void start()
	{
		startMainMenu();
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY)
	{
		print("Clicked at: " + mouseX + " , " + mouseY);
		super.mouseClicked(mouseX,mouseY);
	}

	@Override
	public boolean mouseMoved(int mouseX, int mouseY)
	{
		return (super.mouseMoved(mouseX, mouseY));
	}

	@Override
	public void mouseDragged(int mouseX, int mouseY)
	{
		isDragging = true;
		super.mouseDragged(mouseX, mouseY);
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY)
	{
		isDragging = false;
		super.mouseReleased(mouseX, mouseY);
	}

}
