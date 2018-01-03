package snw.math;

public class VectorDbl
{
	public double x;
	public double y;

	public VectorDbl()
	{
		x = 0;
		y = 0;
	}

	public VectorDbl(double x,double y)
	{
		this.x = x;
		this.y = y;
	}

	public VectorDbl(VectorDbl v)
	{
		this.x = v.x;
		this.y = v.y;
	}

	public VectorDbl(VectorInt v)
	{
		this.x = v.x;
		this.y = v.y;
	}

	public double getNorm2()
	{
		return (x * x + y * y);
	}

	public double getNorm()
	{
		return (Math.sqrt(getNorm2()));
	}

	public VectorDbl add(VectorInt v)
	{
		return (new VectorDbl(x + v.x, y + v.y));
	}

	public VectorDbl minus(VectorInt v)
	{
		return (new VectorDbl(x - v.x, y - v.y));
	}

	public double point(VectorInt v)
	{
		return (x * v.x + y * v.y);
	}

	public double cross(VectorInt v)
	{
		return (x * v.y - y * v.x);
	}

	public VectorDbl neg()
	{
		return (new VectorDbl(-x, -y));
	}
}
