package snw.graphic;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class MaskData
{

	public final int[] mask;
	public final int width;
	public final int height;

	public MaskData(int[] mask, int width, int height)
	{
		this.mask = mask;
		this.width = width;
		this.height = height;
	}

	public void apply(BufferedImage object)
	{
		WritableRaster raster = object.getAlphaRaster();
		int w = raster.getWidth();
		int h = raster.getHeight();
		raster.setPixels(0, 0, w, h, mask);
	}
}
