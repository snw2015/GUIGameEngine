package snw.engine.component;

import java.awt.Graphics;
import java.awt.Image;

public class ScrollBar extends Component
{
	private Image background;
	private Image slider;
	private int sliderWidth;
	private int sliderHeight;

	private double barValue;

	public ScrollBar(String name, Image background, Image slider, int x, int y, int width,
			int height, int sliderWidth, int sliderHeight)
	{
		super(name, x, y, width, height);
		// TODO Auto-generated constructor stub
		this.background = background;
		this.slider = slider;
		this.sliderWidth = sliderWidth;
		this.sliderHeight = sliderHeight;
	}

	@Override
	public void paint(Graphics g)
	{
		// TODO Auto-generated method stub
		g.drawImage(background, 0, 0, width, height, null);
		g.drawImage(slider, (int) ((width - sliderWidth) * barValue),
				(height - sliderHeight) / 2, sliderWidth, sliderHeight, null);
	}

	public double getBarValue()
	{
		return barValue;
	}

	public void setBarValue(double barValue)
	{
		this.barValue = barValue;
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY)
	{
		if (mouseX > width - sliderWidth / 2)
		{
			mouseX = width - sliderWidth / 2;
		} else if (mouseX < sliderWidth / 2)
		{
			mouseX = sliderWidth / 2;
		}
		setBarValue((double) (mouseX - sliderWidth / 2) / (double) (width - sliderWidth));
		super.mousePressed(mouseX, mouseY);
	}

	@Override
	public void mouseDragged(int mouseX, int mouseY)
	{
		mouseClicked(mouseX, mouseY);
	}
}