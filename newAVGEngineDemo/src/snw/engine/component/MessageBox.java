package snw.engine.component;

import java.awt.Image;
import java.awt.event.KeyEvent;

import snw.engine.component.reaction.ReactionMessageClicked;

public class MessageBox extends FrameComponent
{
	private Graphic graphicBG;
	private Text text;
	private Button button;

	private int borderWidth = 18;
	private int borderHeight = 9;

	private int buttonWidth = 50;
	private int buttonHeight = 30;
	private ReactionMessageClicked reaction;

	public MessageBox(String name, Image imageBG, String rawText, int x, int y, int width,
			int height, String buttonText)
	{
		super(name, x, y, width, height);
		// TODO Auto-generated constructor stub
		graphicBG = new Graphic(name + "_background", imageBG, 0, 0, width, height);
		add(graphicBG);
		text = new Text(name + "_text", rawText, borderWidth, borderHeight,
				width - borderWidth * 2, height - borderHeight * 3 - buttonWidth);
		add(text);
		button = new Button(name + "_button", width / 2, height - borderHeight,
				buttonWidth, buttonHeight, buttonText);
		button.setAlignment(ALIGNMENT_BOTTOMMID);
		button.setReactionClicked(() ->
		{
			reaction.react();
		});
		add(button);
	}

	public void setReactionClicked(ReactionMessageClicked reaction)
	{
		this.reaction = reaction;
	}

	public void keyPressed(int key)
	{
		if (key == KeyEvent.VK_ENTER)
		{
			reaction.react();
		}
	}
}
