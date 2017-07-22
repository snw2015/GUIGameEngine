package snw.engine.database;

import java.awt.Image;

import snw.file.FileDirectReader;

public class Database
{
	private static final String USER_CONFIG_PATH = "file/config.txt";

	private static final String CURSOR_DATA_PATH = "file/cursor.png";
	private static final int CURSOR_LENGTH = 6;
	private static final float TEXTBOX_ALPHA = 1.0f;

	public static class CursorData
	{
		private Image[] images;

		public Image[] getImages()
		{
			return images;
		}

		public void setImages(Image[] images)
		{
			this.images = images;
		}
	}

	public static CursorData getCursorData()
	{
		CursorData cursorData = new CursorData();
		cursorData.setImages(
				FileDirectReader.getImageList(CURSOR_DATA_PATH, CURSOR_LENGTH));
		return (cursorData);
	}

	public static String getUserConfigPath()
	{
		return (USER_CONFIG_PATH);
	}

	public static float getTextboxAlpha()
	{
		String config = UserConfig.getData("textbox_alpha");
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
}
