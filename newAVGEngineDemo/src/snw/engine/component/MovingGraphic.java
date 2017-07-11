package snw.engine.component;

import java.awt.Image;

public class MovingGraphic extends Graphic
{
	private boolean isEnded = false;
	private boolean isLoop = true;
	private int counter = 0;
	private int frameNum = 0;
	private int speed = 0;// 0~100
	private final Image[] images;

	public MovingGraphic(String name, Image[] images, int x, int y, int width, int height,
			int speed)
	{
		super(name, images[0], x, y, width, height);
		// TODO Auto-generated constructor stub
		this.images = images;
		this.speed = speed;
	}

	public MovingGraphic(String name, Image[] images, int x, int y, int speed)
	{
		super(name, images[0], x, y);
		// TODO Auto-generated constructor stub
		this.images = images;
		this.speed = speed;
	}

	@Override
	public void update()
	{
		if (speed != 0 && !isEnded)
		{
			int maxmumFrame = 100 / speed;
			if (++counter >= maxmumFrame)
			{
				counter = 0;
				if (++frameNum >= images.length)
				{
					if (isLoop)
					{
						frameNum = 0;
					} else
					{
						isEnded = true;
					}
				}

			}
		}
		image = images[frameNum];
	}

	public int getSpeed()
	{
		return speed;
	}

	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	public int getFrameNum()
	{
		return frameNum;
	}

	public void setFrameNum(int frameNum)
	{
		this.frameNum = frameNum;
	}

	public void setLoop(boolean isLoop)
	{
		this.isLoop = isLoop;
	}

}
