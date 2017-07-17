package snw.engine.component;

import java.awt.event.KeyEvent;

import snw.engine.component.reaction.ReactionSetSwitch;

public class SetSwitch extends FrameComponent
{
	private Text text;
	private boolean isLoop = true;
	private String[] contents;
	private ReactionSetSwitch reaction;
	private int font = 0;

	public int[] clickKey = new int[] { KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT };
	private int index = 0;

	public SetSwitch(String name, int x, int y, int width, int height, String[] contents,
			int font, int initialIndex, boolean isLoop)
	{
		super(name, x, y, width, height);
		// TODO Auto-generated constructor stub
		index = initialIndex;
		text = new Text(name + "_text", contents[index], width/2, height/2, width, height);
		this.font = font;
		text.setFont(font);
		text.setAlignment(ALIGNMENT_CENTER);
		add(text);

		this.contents = contents;
		this.isLoop = isLoop;
	}

	public SetSwitch(String name, int x, int y, int width, int height, String[] contents,
			int initialIndex, boolean isLoop)
	{
		super(name, x, y, width, height);
		// TODO Auto-generated constructor stub
		index = initialIndex;
		text = new Text(name + "_text", contents[index], width/2, height/2, width, height);
		text.setFont(font);
		text.setAlignment(ALIGNMENT_CENTER);
		add(text);

		this.contents = contents;
		this.isLoop = isLoop;
	}

	public SetSwitch(String name, int x, int y, int width, int height, String[] contents,
			int initialIndex)
	{
		super(name, x, y, width, height);
		// TODO Auto-generated constructor stub
		index = initialIndex;
		text = new Text(name + "_text", contents[index], width/2, height/2, width, height);
		text.setFont(font);
		text.setAlignment(ALIGNMENT_CENTER);
		add(text);

		this.contents = contents;
	}

	public SetSwitch(String name, int x, int y, int width, int height, String[] contents)
	{
		super(name, x, y, width, height);
		// TODO Auto-generated constructor stub
		text = new Text(name + "_text", contents[index], width/2, height/2, width, height);
		text.setFont(font);
		text.setAlignment(ALIGNMENT_CENTER);
		add(text);

		this.contents = contents;
	}

	private void switchRight()
	{
		if (isLoop || index < contents.length - 1)
		{
			index = (index + 1) % contents.length;
			switchText();
		}
	}

	private void switchLeft()
	{
		if (isLoop || index > 0)
		{
			index = (index - 1 + contents.length) % contents.length;
			switchText();
		}
	}

	private void switchText()
	{
		String result = contents[index];

		text.setString(result);
		if (reaction != null)
		{
			reaction.react(result);
		}
	}

	@Override
	public void keyPressed(int key)
	{
		if (key == clickKey[0])
		{
			switchLeft();
		} else if (key == clickKey[1])
		{
			switchRight();
		}
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY)
	{
		if (mouseX < width / 2)
		{
			switchLeft();
		} else
		{
			switchRight();
		}
	}
}
