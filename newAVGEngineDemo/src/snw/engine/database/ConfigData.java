package snw.engine.database;

import java.awt.Image;

import snw.file.FileDirectReader;

public class ConfigData
{
	private static CursorData cursorData = new CursorData();

	static {
		cursorData.setImages(FileDirectReader.getImageList("file/cursor.png", 6));
	}

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
		return (cursorData);
	}
}
