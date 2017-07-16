package snw.math;

import java.awt.Rectangle;

public class VectorInt
{
	public int x;
	public int y;

	public VectorInt()
	{
		x = 0;
		y = 0;
	}

	public VectorInt(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public VectorInt(VectorInt v)
	{
		this.x = v.x;
		this.y = v.y;
	}

	public int getNorm2()
	{
		return (x * x + y * y);
	}

	public double getNorm()
	{
		return (Math.sqrt(getNorm2()));
	}

	public VectorInt add(VectorInt v)
	{
		return (new VectorInt(x + v.x, y + v.y));
	}

	public VectorInt minus(VectorInt v)
	{
		return (new VectorInt(x - v.x, y - v.y));
	}

	public int point(VectorInt v)
	{
		return (x * v.x + y * v.y);
	}

	public int cross(VectorInt v)
	{
		return (x * v.y - y * v.x);
	}

	public VectorInt neg()
	{
		return (new VectorInt(-x, -y));
	}

	public boolean inBound(Rectangle bound)
	{
		return (inBound(bound.x, bound.y, bound.width, bound.height));
	}

	public boolean inBound(int x, int y, int width, int height)
	{
		return (x <= this.x && y <= this.y && x + width >= this.x
				&& y + height >= this.y);
	}

	public String toString()
	{
		return ("Vector(" + x + ", " + y + ")");
	}
}
