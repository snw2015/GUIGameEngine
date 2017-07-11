package snw.engine.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.ImageIcon;

import snw.engine.main.MainFrame;
import snw.math.VectorInt;

public class ExtensibleText
{
	private String content = "";
	private ArrayList<Integer> colorIndex = new ArrayList<Integer>();
	private ArrayList<Color> colors = new ArrayList<Color>();
	private ArrayList<Integer> fontIndex = new ArrayList<Integer>();
	private ArrayList<Font> fonts = new ArrayList<Font>();
	private ArrayList<Integer> returns = new ArrayList<Integer>();
	private Color[] colorList;
	private Font[] fontList;
	private VectorInt[] letterPos;
	private int length = 0;
	private int lineWidth;

	public ExtensibleText(String rawText, int defaultColor, int defaultFont,
			int lineWidth)
	{
		this.lineWidth = lineWidth;
		colorList = getColorList();
		fontList = getFontList();
		processText(rawText, defaultColor, defaultFont);
		letterPos = new VectorInt[content.length()];
	}

	private void processText(String raw, int defaultColor, int defaultFont)
	{
		// TODO Auto-generated method stub

		colorIndex.add(defaultColor);
		colors.add(colorList[0]);
		fontIndex.add(defaultFont);
		fonts.add(fontList[0]);
		returns.add(0);

		addString(raw);

	}

	public void processPos(Graphics g)
	{
		// TODO Auto-generated method stub
		Iterator<Integer> itrf = fontIndex.iterator();
		Iterator<Font> itr = fonts.iterator();
		Iterator<Integer> itrr = returns.iterator();
		int indexf = itrf.next();
		int indexr = itrr.next();
		Font font = null;
		FontMetrics metric = null;
		int width = 0;
		int height = 0;
		int maxHeight = 0;

		for (int i = 0; i < length; i++)
		{
			if (i == indexf)
			{
				font = itr.next();
				metric = g.getFontMetrics(font);

				if (itrf.hasNext())
				{
					indexf = itrf.next();
				}
			}

			if (i == indexr)
			{
				height += maxHeight + (i == 0 ? 0 : 5);
				maxHeight = 0;
				width = 0;

				if (itrr.hasNext())
				{
					indexr = itrr.next();
				}
			}

			int h = metric.getHeight();
			if (maxHeight < h)
			{
				maxHeight = h;
			}
			letterPos[i] = new VectorInt(width, height + (int) (h * 0.6) + 5);

			width = width + metric.stringWidth(content.substring(i, i + 1));
			if (lineWidth > 0 && width >= lineWidth)
			{
				height += maxHeight + (i == 0 ? 0 : 5);
				maxHeight = 0;

				letterPos[i] = new VectorInt(0, height + (int) (h * 0.6) + 5);
				width = metric.stringWidth(content.substring(i, i + 1));
			}
		}

	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public void addString(String raw)
	{
		for (int i = 0; i < raw.length(); i++)
		{

			char letter = raw.charAt(i);

			if (letter != '\\')
			{
				if (letter != '\r')
				{
					content = content + letter;
					length++;
				} else
				{
					returns.add(length);
					content = content + '\r';
					length++;
				}
			} else
			{
				if (raw.charAt(i + 1) == 'c')
				{
					i++;
					int index = raw.charAt(++i) - '0';
					colorIndex.add(length);
					colors.add(colorList[index]);
				} else if (raw.charAt(i + 1) == 'f')
				{
					i++;
					int index = raw.charAt(++i) - '0';
					fontIndex.add(length);
					fonts.add(fontList[index]);
				} else
				{
					// TODO
					print("error");
				}
			}
		}
	}

	private Color[] getColorList()
	{
		// TODO
		Color[] c = new Color[3];
		c[0] = Color.BLACK;
		c[1] = Color.WHITE;
		c[2] = Color.RED;
		return (c);
	}

	private Font[] getFontList()
	{
		// TODO
		Font[] f = new Font[3];
		f[0] = new Font("ËÎÌו", Font.PLAIN, 20);
		f[1] = new Font("ËÎÌו", Font.BOLD, 20);
		f[2] = new Font("ËÎÌו", Font.PLAIN, 30);
		return (f);
	}

	public static void print(String s)
	{
		System.out.println(s);
	}

	public ArrayList<Integer> getColorIndex()
	{
		return colorIndex;
	}

	public void setColorIndex(ArrayList<Integer> colorIndex)
	{
		this.colorIndex = colorIndex;
	}

	public ArrayList<Color> getColors()
	{
		return colors;
	}

	public void setColors(ArrayList<Color> colors)
	{
		this.colors = colors;
	}

	public ArrayList<Integer> getFontIndex()
	{
		return fontIndex;
	}

	public void setFontIndex(ArrayList<Integer> fontIndex)
	{
		this.fontIndex = fontIndex;
	}

	public ArrayList<Font> getFonts()
	{
		return fonts;
	}

	public void setFonts(ArrayList<Font> fonts)
	{
		this.fonts = fonts;
	}

	public VectorInt[] getLetterPos()
	{
		return letterPos;
	}

	public void setLetterPos(VectorInt[] letterPos)
	{
		this.letterPos = letterPos;
	}

	public int getLength()
	{
		return length;
	}

	public int getLineWidth()
	{
		return lineWidth;
	}

	public void setLineWidth(int lineWidth)
	{
		this.lineWidth = lineWidth;
	}
}
