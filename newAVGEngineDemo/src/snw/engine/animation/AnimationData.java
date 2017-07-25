package snw.engine.animation;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Arrays;

import snw.graphic.Filter;
import snw.graphic.MaskData;

public class AnimationData
{
	private AffineTransform transformation;
	private int alpha = 255;
	private MaskData mask;
	private Filter[] filters;
	private int filterNum = 0;
	private boolean isMasked = false;
	private Image maskImage;

	public AnimationData(String rawData, Image[] masks)
	{
		String[] matrix = rawData.split(",");

		transformation = new AffineTransform(Double.valueOf(matrix[0]),
				Double.valueOf(matrix[1]), Double.valueOf(matrix[2]),
				Double.valueOf(matrix[3]), Double.valueOf(matrix[4]),
				Double.valueOf(matrix[5]));

		if (matrix.length >= 7)
		{
			alpha = Integer.valueOf(matrix[6]);
			if (matrix.length >= 8)
			{
				maskImage = masks[Integer.valueOf(matrix[7])];
				isMasked = true;
			}
		}
	}

	public void apply(Graphics2D canvas, BufferedImage object, int deltaX, int deltaY)
	{
		// TODO Auto-generated method stub
		if (alpha != 0)
		{
			if (isMasked)
			{
				int width = object.getWidth();
				int height = object.getHeight();
				BufferedImage rescaledImage = new BufferedImage(width, height,
						BufferedImage.TYPE_BYTE_GRAY);
				rescaledImage.getGraphics().drawImage(maskImage, 0, 0, width, height, null);
				mask = new MaskData(
						rescaledImage.getRaster().getPixels(0, 0, width, height, new int[width * height]),
						width, height);
				mask.apply(object);
			}
			for (int i = 0; i < filterNum; i++)
			{
				filters[i].apply(object);
			}
			if (alpha < 255)
			{
				canvas.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
						(float) alpha / 255));
			}
			transformation.translate(deltaX, deltaY);
			canvas.drawImage(object, transformation, null);
			transformation.translate(-deltaX, -deltaY);
		}
	}

	public String toString()
	{
		return (transformation.toString() + "," + alpha);
	}
}
