package snw.engine.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class TextBox extends FrameComponent
{
	private Graphic graphicBG;
	protected Text text;

	private int borderWidth = 18;
	private int borderHeight = 9;

	public TextBox(String name, Image background)
	{
		super(name, 0, 0, new ImageIcon(background).getIconWidth(),
				new ImageIcon(background).getIconHeight());
		graphicBG = new Graphic(name + "_background", background, 0, 0);
		add(graphicBG);
		text = new Text(name + "_text", "", borderWidth, borderHeight,
				width - borderWidth * 2, height - borderHeight * 2);
		add(text);
	}

	public TextBox(String name, Image background, int x, int y)
	{
		super(name, x, y, new ImageIcon(background).getIconWidth(),
				new ImageIcon(background).getIconHeight());
		graphicBG = new Graphic(name + "_background", background, 0, 0);
		add(graphicBG);
		text = new Text(name + "_text", "", borderWidth, borderHeight,
				width - borderWidth * 2, height - borderHeight * 2);
		add(text);
	}

	public TextBox(String name, Image background, int x, int y, int width, int height)
	{
		super(name, x, y, width, height);
		graphicBG = new Graphic(name + "_background", background, 0, 0, width, height);
		add(graphicBG);
		text = new Text(name + "_text", "", borderWidth, borderHeight,
				width - borderWidth * 2, height - borderHeight * 2);
		add(text);
	}

	public TextBox(String name, Image background, String rawText)
	{
		super(name, 0, 0, new ImageIcon(background).getIconWidth(),
				new ImageIcon(background).getIconHeight());
		graphicBG = new Graphic(name + "_background", background, 0, 0);
		add(graphicBG);
		text = new Text(name + "_text", rawText, borderWidth, borderHeight,
				width - borderWidth * 2, height - borderHeight * 2);
		add(text);
	}

	public TextBox(String name, Image background, String rawText, int x, int y)
	{
		super(name, x, y, new ImageIcon(background).getIconWidth(),
				new ImageIcon(background).getIconHeight());
		graphicBG = new Graphic(name + "_background", background, 0, 0);
		add(graphicBG);
		text = new Text(name + "_text", rawText, borderWidth, borderHeight,
				width - borderWidth * 2, height - borderHeight * 2);
		add(text);
	}

	public TextBox(String name, Image background, String rawText, int x, int y, int width,
			int height)
	{
		super(name, x, y, width, height);

		graphicBG = new Graphic(name + "_background", background, 0, 0, width, height);
		add(graphicBG);
		text = new Text(name + "_text", rawText, borderWidth, borderHeight,
				width - borderWidth * 2, height - borderHeight * 2);
		add(text);
	}

	public int getBorderWidth()
	{
		return borderWidth;
	}

	public void setBorderWidth(int borderWidth)
	{
		this.borderWidth = borderWidth;
		text.setX(borderWidth);
		text.setLineWidth(width - borderWidth * 2);
	}

	public int getBorderHeight()
	{
		return borderHeight;
	}

	public void setBorderHeight(int borderHeight)
	{
		this.borderHeight = borderHeight;
		text.setY(borderWidth);
	}

	public void setColor(int color)
	{
		text.setColor(color);
	}

	public void setFont(int font)
	{
		text.setFont(font);
	}

	public void setBackgroundAlpha(float alpha)
	{
		graphicBG.setAlpha(alpha);
	}

	public void setString(String rawText)
	{
		text.setString(rawText);
	}
}
