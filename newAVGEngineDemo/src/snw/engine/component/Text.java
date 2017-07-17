package snw.engine.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import snw.math.VectorInt;
import snw.text.ExtensibleText;

public class Text extends Component
{

	private ExtensibleText content;
	private int font;
	private int color;
	private int lineWidth;
	private boolean hasProcessed = false;
	private int renderLength;

	public Text(String name, String rawText, int x, int y, int width, int height)
	{
		super(name, x, y, width, height);
		content = new ExtensibleText(rawText, 0, 0, 0);
		renderLength = content.getLength();
		font = 0;
		color = 0;
		setLineWidth(width);
	}

	@Override
	public void paint(Graphics g)
	{
		if (!hasProcessed)
		{
			VectorInt renderBound = content.processPos(g);
			setSize(renderBound);
			hasProcessed = true;
		}

		Iterator<Integer> itrci = content.getColorIndex().iterator();
		Iterator<Color> itrc = content.getColors().iterator();
		Iterator<Integer> itrfi = content.getFontIndex().iterator();
		Iterator<Font> itrf = content.getFonts().iterator();

		int indexc = itrci.next();
		int indexf = itrfi.next();

		String string = content.getContent();
		VectorInt[] pos = content.getLetterPos();

		for (int i = 0; i < renderLength; i++)
		{
			if (indexc == i)
			{
				g.setColor(itrc.next());
				if (itrci.hasNext())
				{
					indexc = itrci.next();
				}
			}
			if (indexf == i)
			{
				g.setFont(itrf.next());
				if (itrfi.hasNext())
				{
					indexf = itrfi.next();
				}
			}

			g.drawString(string.substring(i, i + 1), pos[i].x, pos[i].y);
		}
	}

	public void addString(String raw)
	{
		content.addString(raw);
		hasProcessed = false;
	}

	public void setString(String raw)
	{
		content.setContent(raw);
		setRenderLength(content.getLength());
		hasProcessed = false;
	}

	public ExtensibleText getContent()
	{
		return content;
	}

	public void setContent(ExtensibleText newContent)
	{
		content = newContent;
	}

	public int getFont()
	{
		return font;
	}

	public void setFont(int font)
	{
		this.font = font;
	}

	public int getColor()
	{
		return color;
	}

	public void setColor(int color)
	{
		this.color = color;
	}

	public int getLineWidth()
	{
		return lineWidth;
	}

	public void setLineWidth(int lineWidth)
	{
		this.lineWidth = lineWidth;
		content.setLineWidth(lineWidth);
	}

	public int getRenderLength()
	{
		return renderLength;
	}

	public void setRenderLength(int renderLength)
	{
		this.renderLength = renderLength;
	}

	public boolean addRenderLength()
	{
		if (renderLength < content.getLength())
		{
			setRenderLength(getRenderLength() + 1);
			return (true);
		}
		return (false);
	}
}
