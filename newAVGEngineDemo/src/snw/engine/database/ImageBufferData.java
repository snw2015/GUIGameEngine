package snw.engine.database;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;

import snw.file.FileDirectReader;

public class ImageBufferData
{
	private static HashMap<String, Image> bufferData = new HashMap<String, Image>();

	/**
	 * 
	 * @param name
	 * @return false if overwrote or image is null
	 */
	public static boolean storeImage(String name)
	{
		if (bufferData.containsKey(name))
		{
			return (false);
		} else
		{
			Image image = loadImage(name);
			if (image != null)
			{
				bufferData.put(name, loadImage(name));
				return (true);
			} else
			{
				// TODO throw errors
				return (false);
			}
		}
	}

	public static boolean storeImage(String name, Image image)
	{
		if (bufferData.containsKey(name))
		{
			bufferData.put(name, image);
			return (false);
		} else
		{
			bufferData.put(name, image);
			return (true);
		}
	}

	public static boolean releaseImage(String name)
	{
		if (bufferData.containsKey(name))
		{
			bufferData.remove(name);
			return (true);
		} else
		{
			return (false);
		}
	}

	public static Image getImage(String name)
	{
		Image image = bufferData.get(name);
		return (image != null ? image : loadImage(name));
	}

	private static Image loadImage(String name)
	{
		// TODO Auto-generated method stub
		return (FileDirectReader.getImage("file/" + name + ".png"));
	}

}
