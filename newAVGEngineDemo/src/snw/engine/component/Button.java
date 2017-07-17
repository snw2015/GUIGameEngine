package snw.engine.component;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import snw.engine.component.reaction.*;
import snw.math.VectorInt;
import snw.text.ExtensibleText;

public class Button extends FrameComponent
{
	private Graphic background = null;
	private Text text = null;
	private ReactionButtonClicked reactionClicked = null;
	private ReactionButtonIn reactionIn = null;
	private ReactionButtonOut reactionOut = null;
	private VectorInt borderSize = new VectorInt(0, 0);

	private int clickKey = KeyEvent.VK_ENTER;

	public Button(String name, int x, int y, int width, int height)
	{
		super(name, x, y, width, height);
	}

	public Button(String name, int x, int y, int width, int height, String rawtext)
	{
		super(name, x, y, width, height);
		setText(rawtext);
	}

	public Button(String name, int x, int y, Image background, String rawtext,
			int borderX, int borderY)
	{
		super(name, x, y, background.getWidth(null), background.getHeight(null));
		borderSize.x = borderX;
		borderSize.y = borderY;
		setBackground(background);
		setText(rawtext);
	}

	public Button(String name, int x, int y, int width, int height, Image background)
	{
		super(name, x, y, width, height);
		setBackground(background);
	}

	public Button(String name, int x, int y, int width, int height, Image background,
			String rawtext, int borderX, int borderY)
	{
		super(name, x, y, width, height);
		borderSize.x = borderX;
		borderSize.y = borderY;
		setBackground(background);
		setText(rawtext);
	}

	public Button(String name, int x, int y, int width, int height, Image background,
			String rawtext)
	{
		super(name, x, y, width, height);
		setBackground(background);
		setText(rawtext);
	}

	public Button(String name, int x, int y, Image background)
	{
		super(name, x, y, background.getWidth(null), background.getHeight(null));
		setBackground(background);
	}

	public Button(String name, int x, int y, Image background, String rawtext)
	{
		super(name, x, y, background.getWidth(null), background.getHeight(null));
		setBackground(background);
		setText(rawtext);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY)
	{
		if (reactionClicked != null)
		{
			reactionClicked.react();
		}
	}

	@Override
	public void mouseEntered()
	{
		super.mouseEntered();
		if (reactionIn != null)
		{
			reactionIn.react();
		}
	}

	@Override
	public void mouseExited()
	{
		super.mouseExited();
		if (reactionOut != null)
		{
			reactionOut.react();
		}
	}

	@Override
	public void keyPressed(int key)
	{
		if (key == clickKey)
		{
			if (reactionClicked != null)
			{
				reactionClicked.react();
			}
		}
	}

	public Graphic getBackground()
	{
		return background;
	}

	public void setBackground(Image image)
	{
		Graphic comGraphic = new Graphic(name + "_background", image, 0, 0, width,
				height);
		if (background != null)
		{
			remove(name + "_background");
		}
		add(0, comGraphic);
		background = comGraphic;
	}

	public Text getText()
	{
		return text;
	}

	public void setText(String rawtext)
	{
		Text comText = new Text(name + "_text", rawtext, width / 2, height / 2,
				width - borderSize.x * 2, height - borderSize.y * 2);
		comText.setAlignment(ALIGNMENT_CENTER);
		if (text != null)
		{
			remove(name + "_text");
		}
		add(comText);
		text = comText;
	}

	public void setReactionClicked(ReactionButtonClicked reactionClicked)
	{
		this.reactionClicked = reactionClicked;
	}

	public void setReactionIn(ReactionButtonIn reactionIn)
	{
		this.reactionIn = reactionIn;
	}

	public void setReactionOut(ReactionButtonOut reactionOut)
	{
		this.reactionOut = reactionOut;
	}

	public int getClickKey()
	{
		return clickKey;
	}

	public void setClickKey(int clickKey)
	{
		this.clickKey = clickKey;
	}

}
