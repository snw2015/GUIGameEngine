package snw.file;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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

	public static Image[] getImageList(String fileName, int length)
	{
		if (length > 1)
		{
			Image[] images = new Image[length];
			for (int i = 0; i < length; i++)
			{
				String[] nameSplit = fileName.split("\\.", 2);
				images[i] = getImage(nameSplit[0] + "_" + (i + 1) + "." + nameSplit[1]);
			}
			return (images);
		} else if (length == 1)
		{
			return (new Image[] { getImage(fileName) });
		}
		return (null);
	}

	public static BufferedReader getBufferedReaderOfFile(String fileName)
			throws FileNotFoundException
	{
		File file = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(file));
		return (br);
	}
}
