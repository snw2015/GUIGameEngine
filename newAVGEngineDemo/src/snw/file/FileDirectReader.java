package snw.file;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class FileDirectReader
{
	public static Image getImage(String filename)
	{
		ImageIcon icon = new ImageIcon(filename);
		return (icon.getImage());
	}

	public static Image[] getImageList(String[] filenames)
	{
		int length = filenames.length;
		Image[] images = new Image[length];
		for (int i = 0; i < length; i++)
		{
			images[i] = getImage(filenames[i]);
		}
		return (images);
	}

	public static Image[] getImageList(String filename, int length)
	{
		if (length > 1)
		{
			Image[] images = new Image[length];
			for (int i = 0; i < length; i++)
			{
				String[] nameSplit = filename.split("\\.", 2);
				images[i] = getImage(nameSplit[0] + "_" + (i + 1) + "." + nameSplit[1]);
			}
			return (images);
		} else
		{
			return (null);
		}
	}
}
