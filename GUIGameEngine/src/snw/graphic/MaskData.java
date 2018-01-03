<<<<<<< HEAD:GUIGameEngine/src/snw/graphic/MaskData.java
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
=======
package snw.graphic;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Arrays;

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
>>>>>>> parent of 5b3b56b... version 0.1.0 pure reduction:newAVGEngineDemo/src/snw/graphic/MaskData.java
