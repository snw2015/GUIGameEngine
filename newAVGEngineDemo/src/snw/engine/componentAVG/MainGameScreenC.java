package snw.engine.componentAVG;

import java.awt.event.KeyEvent;

import snw.engine.component.FrameComponent;
import snw.engine.component.Graphic;
import snw.engine.component.MovingTextBox;
import snw.engine.component.OptionBox;
import snw.engine.component.SelectionBox;
import snw.engine.component.reaction.ReactionCommand;
import snw.engine.database.Database;

public class MainGameScreenC extends PanelC
{
	private Graphic background;
	private FrameComponent graphicLayer;
	private MovingTextBox textbox;
	private SelectionBox selectionbox;
	private ReactionCommand reaction = null;

	public static final int COMMAND_END = 0;
	public static final int COMMAND_SAVE = 1;

	public MainGameScreenC()
	{
		super("mainGameScreen");
	}

	@Override
	public void preProcess()
	{
		preLoadImageNames = new String[] { "background_main_screen", "option_common" };
		super.preProcess();

		addBackground();
		addGraphicLayer();
		addTextbox();

		displaySelection("test", preLoadImageNames);
	}

	private void addBackground()
	{
		background = new Graphic("background", getImage("background_main_screen"), 0, 0,
				width, height);
		add(background);
	}

	private void addGraphicLayer()
	{
		graphicLayer = new FrameComponent("graphicLayer", 0, 0, width, height);
		add(graphicLayer);
	}

	private void addTextbox()
	{
		textbox = new MovingTextBox("textbox", getImage("textbox_common"), width / 2,
				height - 15);
		textbox.setAlignment(ALIGNMENT_BOTTOMMID);
		textbox.setBackgroundAlpha(Database.getTextboxAlpha());
		add(textbox);
	}

	private void setTextBoxContent(String rawText)
	{
		textbox.setTextContent(rawText);
		textbox.resetRender();
	}

	private void displaySelection(String titleText, String[] selections)
	{
		remove("selectionbox");
		selectionbox = new SelectionBox("selectionbox", getImage("textbox_common"),
				titleText, width / 2, 250, 525, 675, selections);
		selectionbox.setAlignment(ALIGNMENT_TOPMID);
		add(selectionbox);
	}

	public void setReaction(ReactionCommand reaction)
	{
		this.reaction = reaction;
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY)
	{
		super.mouseClicked(mouseX, mouseY);
	}

	@Override
	public void keyPressed(int key)
	{
		if (reaction != null)
		{
			if (key == KeyEvent.VK_ESCAPE)
			{
				reaction.react(COMMAND_END);
			} else if (key == KeyEvent.VK_S)
			{
				reaction.react(COMMAND_SAVE);
			}
		} else
		{
			super.keyPressed(key);
		}
	}

	private void setTextboxVisible(boolean isVisible)
	{
		textbox.setVisible(isVisible);
	}

	private void setTextboxBackAlpha(float alpha)
	{
		textbox.setBackgroundAlpha(alpha);
	}
}
