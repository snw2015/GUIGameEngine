package snw.engine.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import snw.math.VectorInt;

public class Graphic extends Component
{
	protected Image image;
	protected Shape shape;
	private Color color;
	private float alpha = 1;
	private int mode;

	public static final int MODE_IMAGE = 0;
	public static final int MODE_SHAPE = 1;

	public Graphic(String name, Image image, int x, int y,boolean focusable)
	{
		super(name, x, y, image.getWidth(null), image.getHeight(null),focusable);
		this.image = image;
		mode = MODE_IMAGE;
	}

	public Graphic(String name, Shape shape, int x, int y,boolean focusable)
	{
		super(name, x, y, (int) shape.getBounds().getWidth(),
				(int) shape.getBounds().getHeight(),focusable);
		color = Color.BLACK;
		this.shape = shape;
		mode = MODE_SHAPE;
	}

	public Graphic(String name, Color color, Shape shape, int x, int y,boolean focusable)
	{
		super(name, x, y, (int) shape.getBounds().getWidth(),
				(int) shape.getBounds().getHeight(),focusable);
		this.color = color;
		this.shape = shape;
		mode = MODE_SHAPE;
	}

	public Graphic(String name, Image image, int x, int y, int width, int height,boolean focusable)
	{
		// TODO Auto-generated constructor stub
		super(name, x, y, width, height,focusable);
		BufferedImage iBuffer = new BufferedImage(width, height,
				BufferedImage.TYPE_4BYTE_ABGR);
		Graphics iGraphics = iBuffer.getGraphics();
		iGraphics.drawImage(image, 0, 0, width, height, null);

		this.image = iBuffer;
		mode = MODE_IMAGE;
	}

	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

		switch (mode)
		{
		case MODE_IMAGE:
			g2d.drawImage(image, 0, 0, null);
			break;
		case MODE_SHAPE:
			g2d.setColor(color);
			g2d.fill(shape);
			break;
		default:
			print("graphic mode wrong");
		}

	}

	public void setAlpha(float alpha)
	{
		this.alpha = alpha;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

}
