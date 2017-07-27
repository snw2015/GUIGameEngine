package snw.engine.script;

import snw.engine.database.Database;
import snw.engine.database.Database.LimitData;

public class DataSet
{
	private boolean[] chapterSwitch;
	private boolean[] fileSwitch;
	private boolean[] globalSwitch;
	private int[] chapterInt;
	private int[] fileInt;
	private int[] globalInt;
	private String[] chapterString;
	private String[] fileString;
	private String[] globalString;
	private int selectedValue;

	public boolean getChapterSwitch(int index)
	{
		return chapterSwitch[index];
	}

	public void setChapterSwitch(int index, boolean value)
	{
		chapterSwitch[index] = value;
	}

	public boolean getFileSwitch(int index)
	{
		return fileSwitch[index];
	}

	public void setFileSwitch(int index, boolean value)
	{
		fileSwitch[index] = value;
	}

	public boolean getGlobalSwitch(int index)
	{
		return globalSwitch[index];
	}

	public void setGlobalSwitch(int index, boolean value)
	{
		globalSwitch[index] = value;
	}

	public int getChapterInt(int index)
	{
		return chapterInt[index];
	}

	public void setChapterInt(int index, int value)
	{
		chapterInt[index] = value;
	}

	public int getFileInt(int index)
	{
		return fileInt[index];
	}

	public void setFileInt(int index, int value)
	{
		fileInt[index] = value;
	}

	public int getGlobalInt(int index)
	{
		return globalInt[index];
	}

	public void setGlobalInt(int index, int value)
	{
		globalInt[index] = value;
	}

	public String getFileString(int index)
	{
		return fileString[index];
	}

	public void setFileString(int index, String value)
	{
		fileString[index] = value;
	}

	public String getChapterString(int index)
	{
		return chapterString[index];
	}

	public void setChapterString(int index, String value)
	{
		chapterString[index] = value;
	}

	public String getGlobalString(int index)
	{
		return globalString[index];
	}

	public void setGlobalString(int index, String value)
	{
		globalString[index] = value;
	}

	public int getSelectedValue()
	{
		return selectedValue;
	}

	public void setSelectedValue(int value)
	{
		selectedValue = value;
	}

	public void initialize()
	{
		// TODO Auto-generated method stub
		LimitData limitData = Database.getVarNumLimit();

		chapterSwitch = new boolean[limitData.getChapterSwitchLimit()];
		fileSwitch = new boolean[limitData.getFileSwitchLimit()];
		globalSwitch = new boolean[limitData.getGlobalSwitchLimit()];
		chapterInt = new int[limitData.getChapterIntLimit()];
		fileInt = new int[limitData.getFileIntLimit()];
		globalInt = new int[limitData.getGlobalIntLimit()];
		chapterString = new String[limitData.getChapterStringLimit()];
		fileString = new String[limitData.getFileStringLimit()];
		globalString = new String[limitData.getGlobalStringLimit()];
		selectedValue = -1;
	}
}
