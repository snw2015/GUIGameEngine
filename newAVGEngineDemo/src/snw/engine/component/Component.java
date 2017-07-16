package snw.engine.component;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import snw.engine.animation.Animation;
import snw.engine.animation.AnimationData;
import snw.engine.main.MainFrame;
import snw.math.VectorDbl;
import snw.math.VectorInt;

public abstract class Component
{
	protected final String name;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	private boolean isAnimated = false;
	private AnimationData animationData;
	private Animation animation;

	public static final int ALIGNMENT_LEFTTOP = 0;
	public static final int ALIGNMENT_LEFTBOT = 1;
	public static final int ALIGNMENT_LEFTMID = 2;
	public static final int ALIGNMENT_RIGHTTOP = 3;
	public static final int ALIGNMENT_RIGHTBOT = 4;
	public static final int ALIGNMENT_RIGHTMID = 5;
	public static final int ALIGNMENT_TOPMID = 6;
	public static final int ALIGNMENT_BOTTOMMID = 7;
	public static final int ALIGNMENT_CENTER = 8;

	public static final VectorDbl[] alignmentList = new VectorDbl[] { new VectorDbl(0, 0),
			new VectorDbl(0, 1), new VectorDbl(0, 0.5), new VectorDbl(1, 0),
			new VectorDbl(1, 1), new VectorDbl(1, 0.5), new VectorDbl(0.5, 0),
			new VectorDbl(0.5, 1), new VectorDbl(0.5, 0.5) };

	private int alignment = ALIGNMENT_LEFTTOP;

	public Component(String name, int x, int y, int width, int height)
	{
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void render(Graphics g)
	{
		// print("rendering " + name + " x: " + x + " y:" + y);
		BufferedImage iBuffer = new BufferedImage(width, height,
				BufferedImage.TYPE_4BYTE_ABGR);
		Graphics gBuffer = iBuffer.getGraphics();

		paint(gBuffer);

		int alignedX = getAlignedX();
		int alignedY = getAlignedY();
		if (isAnimated)
		{
			Graphics2D g2d = (Graphics2D) g;
			applyAnimation(g2d, iBuffer, alignedX, alignedY);
			updateAnimation();
		} else
		{
			g.drawImage(iBuffer, alignedX, alignedY, width, height, null);
		}
		update();
	}

	public abstract void paint(Graphics g);

	public void update()
	{
	}

	public void updateAnimation()
	{
		if (animation != null)
		{
			animationData = animation.getNextFrame();
		}
	}

	private void applyAnimation(Graphics2D canvas, BufferedImage object, int deltaX,
			int deltaY)
	{
		// TODO Auto-generated method stub
		// print(animationData.toString());

		if (animationData != null)
		{
			animationData.apply(canvas, object, deltaX, deltaY);
		}
	}

	public void keyTyped(char keyChar)
	{

	}

	public void keyPressed(int key)
	{
	}

	public void keyReleased(int key)
	{
	}

	public void mouseClicked(int mouseX, int mouseY)
	{
		print("click " + name);
	}

	public void mousePressed(int mouseX, int mouseY)
	{
	}

	public void mouseReleased(int mouseX, int mouseY)
	{
	}

	public void mouseEntered()
	{
		print("move into " + name);
	}

	public void mouseExited()
	{
		print("move out of " + name);
	}

	public boolean mouseMoved(int mouseX, int mouseY)
	{
		return false;
	}

	public void mouseDragged(int mouseX, int mouseY)
	{
	}

	public String getName()
	{
		return name;
	}

	public VectorInt getPosition()
	{
		return (new VectorInt(x, y));
	}

	public static void print(String s)
	{
		System.out.println(s);
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public VectorInt getPos()
	{
		return (new VectorInt(x, y));
	}

	public Rectangle getBound()
	{
		return (new Rectangle(getAlignedX(), getAlignedY(), width, height));
	}

	public void setPos(VectorInt pos)
	{
		setX(pos.x);
		setY(pos.y);
	}

	public void setPos(int x, int y)
	{
		setX(x);
		setY(y);
	}

	public Animation getAnimation()
	{
		return animation;
	}

	public void setAnimation(Animation animation)
	{
		synchronized (this)
		{
			if (animation != null)
			{
				this.animation = animation;
				animationData = animation.getFirstFrame();
				isAnimated = true;
			} else
			{
				this.animation = null;
				animationData = null;
				isAnimated = false;
			}
		}
	}

	public void setAnimation(Animation animation, boolean isLoop)
	{
		setAnimation(animation);
		animation.setLoop(isLoop);
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	public void setSize(VectorInt size)
	{
		this.width = size.x;
		this.height = size.y;
	}

	public VectorInt getSize()
	{
		return (new VectorInt(width, height));
	}

	public boolean isAnimated()
	{
		return isAnimated;
	}

	public int getAlignment()
	{
		return alignment;
	}

	public int getAlignedX()
	{
		return (x - (int) ((double) width * alignmentList[alignment].x));
	}

	public int getAlignedY()
	{
		return (y - (int) ((double) height * alignmentList[alignment].y));
	}

	public void setAlignment(int alignment)
	{
		this.alignment = alignment;
	}

}
