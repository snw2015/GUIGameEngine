package snw.engine.component;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import snw.engine.component.reaction.ReactionOptionClicked;
import snw.engine.component.reaction.ReactionSelectionClicked;

public class SelectionBox extends FrameComponent
{
	private Graphic graphicBG;
	private Text text;

	private ListPanel selections;
	private int numSelection;

	private int borderWidth = 18;
	private int borderHeight = 9;
	private int textMaxHeight = 60;
	private ReactionSelectionClicked reaction;

	public SelectionBox(String name, Image imageBG, String rawText, int x, int y,
			int width, int height, String[] selectionText)
	{
		super(name, x, y, width, height);
		// TODO Auto-generated constructor stub
		graphicBG = new Graphic(name + "_background", imageBG, 0, 0, width, height);
		add(graphicBG);
		text = new Text(name + "_text", rawText, borderWidth, borderHeight,
				width - borderWidth * 2, textMaxHeight);
		add(text);

		numSelection = selectionText.length;
		Button[] buttons = new Button[numSelection];
		for (int i = 0; i < numSelection; i++)
		{
			buttons[i] = new Button(name + "_button" + i, width / 2 - borderWidth,
					(height - textMaxHeight) * i / numSelection, width - borderWidth * 2,
					(height - textMaxHeight) / numSelection, selectionText[i]);
			buttons[i].setAlignment(ALIGNMENT_TOPMID);
			final int index = i;
			buttons[i].setReactionClicked(() ->
			{
				reaction.react(index);
			});
		}
		selections = ListPanelGenerator.getVerticalInstance(name + "_selections",
				borderWidth, textMaxHeight, width - borderWidth * 2,
				height - textMaxHeight, buttons);
		selections.setFlash(true);
		add(selections);
	}

	public void setReactionClicked(ReactionSelectionClicked reaction)
	{
		this.reaction = reaction;
	}

	@Override
	public void keyPressed(int key)
	{
		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP || key == KeyEvent.VK_ENTER)
		{
			selections.keyPressed(key);
		} else
		{
			super.keyPressed(key);
		}
	}

}
