package snw.engine.database;

import java.awt.Image;
import java.util.Arrays;
import java.util.HashMap;

import snw.file.FileDirectReader;

public class Database
{
	private static final String USER_CONFIG_PATH = "file/config.txt";

	private static final String CURSOR_NORMAL_PATH = "file/image/cursor.png";
	private static final int CURSOR_NORMAL_LENGTH = 6;

	private static final String CURSOR_DRAG_PATH = "file/image/cursor_drag.png";
	private static final int CURSOR_DRAG_LENGTH = 1;

	private static final float TEXTBOX_ALPHA = 1.0f;

	public static class CursorData
	{
		private HashMap<String, Image[]> imageSetsByTypeNames = new HashMap<String, Image[]>();
		private String defaultType = "";

		public Image[] getImages(String typeName)
		{
			if (imageSetsByTypeNames.containsKey(typeName))
			{
				return (imageSetsByTypeNames.get(typeName));
			} else
			{
				return getImages();
			}
		}

		public Image[] getImages()
		{
			if (imageSetsByTypeNames.containsKey(defaultType))
			{
				return (imageSetsByTypeNames.get(defaultType));
			} else
			{
				return (null);
			}
		}

		public void setImages(String typeName, Image[] images)
		{
			imageSetsByTypeNames.put(typeName, images);
		}

		public void setDefaultType(String typeName)
		{
			defaultType = typeName;
		}
	}

	public static CursorData getCursorData()
	{
		CursorData cursorData = new CursorData();
		cursorData.setImages("normal",
				FileDirectReader.getImageList(CURSOR_NORMAL_PATH, CURSOR_NORMAL_LENGTH));
		cursorData.setImages("drag",
				FileDirectReader.getImageList(CURSOR_DRAG_PATH, CURSOR_DRAG_LENGTH));
		cursorData.setDefaultType("normal");

		return (cursorData);
	}

	public static String getUserConfigPath()
	{
		return (USER_CONFIG_PATH);
	}

	public static float getTextboxAlpha()
	{
		String config = getUserData("textbox_alpha");
		if (config != null)
		{
			float alpha = 0;
			try
			{
				alpha = Float.valueOf(config);
			} catch (NumberFormatException e)
			{
				return (TEXTBOX_ALPHA);
			}
			return (alpha);
		}
		return (TEXTBOX_ALPHA);
	}

	public static void loadUserData()
	{
		UserConfig.load(USER_CONFIG_PATH);
	}

	public static String getUserData(String dataKey)
	{
		return (UserConfig.getData(dataKey));
	}
}
