package snw.engine.script;

import java.util.Arrays;
import java.util.HashMap;

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

	private HashMap<String, TypeInfo> typeOfVars = new HashMap<String, TypeInfo>();
	private int[][] varNum;

	public static final int FIELD_CHAPTER = 0;
	public static final int FIELD_FILE = 1;
	public static final int FIELD_GLOBAL = 2;
	public static final int TYPE_BOOL = 0;
	public static final int TYPE_INT = 1;
	public static final int TYPE_STR = 2;

	public DataSet()
	{
		initialize();
	}

	public class TypeInfo
	{
		int type;
		int field;
		int index;

		TypeInfo(int type, int field, int index)
		{
			this.type = type;
			this.field = field;
			this.index = index;
		};
	}

	public boolean getSwitch(int field, int index)
	{
		switch (field)
		{
		case FIELD_CHAPTER:
			return (getChapterSwitch(index));
		case FIELD_FILE:
			return (getFileSwitch(index));
		case FIELD_GLOBAL:
			return (getGlobalSwitch(index));
		}
		return (false);
	}

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

	public int getInt(int field, int index)
	{
		switch (field)
		{
		case FIELD_CHAPTER:
			return (getChapterInt(index));
		case FIELD_FILE:
			return (getFileInt(index));
		case FIELD_GLOBAL:
			return (getGlobalInt(index));
		}
		return (0);
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

	public String getString(int field, int index)
	{
		switch (field)
		{
		case FIELD_CHAPTER:
			return (getChapterString(index));
		case FIELD_FILE:
			return (getFileString(index));
		case FIELD_GLOBAL:
			return (getGlobalString(index));
		}
		return ("");
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

	public boolean addSwitch(int field, String name)
	{
		boolean[] orientArray = null;
		switch (field)
		{
		case FIELD_CHAPTER:
			orientArray = chapterSwitch;
			break;
		case FIELD_FILE:
			orientArray = fileSwitch;
			break;
		case FIELD_GLOBAL:
			orientArray = globalSwitch;
			break;
		}

		if (varNum[field][TYPE_BOOL] < orientArray.length
				&& !typeOfVars.containsKey(name))
		{
			typeOfVars.put(name,
					new TypeInfo(TYPE_BOOL, field, varNum[field][TYPE_BOOL]++));
		}
		return (false);
	}

	public boolean addInt(int field, String name)
	{
		int[] orientArray = null;
		switch (field)
		{
		case FIELD_CHAPTER:
			orientArray = chapterInt;
			break;
		case FIELD_FILE:
			orientArray = fileInt;
			break;
		case FIELD_GLOBAL:
			orientArray = globalInt;
			break;
		}

		if (varNum[field][TYPE_INT] < orientArray.length && !typeOfVars.containsKey(name))
		{
			typeOfVars.put(name,
					new TypeInfo(TYPE_INT, field, varNum[field][TYPE_INT]++));
		}
		return (false);
	}

	public boolean addString(int field, String name)
	{
		String[] orientArray = null;
		switch (field)
		{
		case FIELD_CHAPTER:
			orientArray = chapterString;
			break;
		case FIELD_FILE:
			orientArray = fileString;
			break;
		case FIELD_GLOBAL:
			orientArray = globalString;
			break;
		}

		if (varNum[field][TYPE_STR] < orientArray.length && !typeOfVars.containsKey(name))
		{
			typeOfVars.put(name,
					new TypeInfo(TYPE_STR, field, varNum[field][TYPE_STR]++));
		}
		return (false);
	}

	public boolean addVar(int type, int field, String name)
	{
		switch (type)
		{
		case TYPE_BOOL:
			return (addSwitch(field, name));
		case TYPE_INT:
			return (addInt(field, name));
		case TYPE_STR:
			return (addString(field, name));
		}
		return (false);
	}

	public TypeInfo getVarInfo(String name)
	{
		return (typeOfVars.get(name));
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

		varNum = new int[3][3];
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				varNum[i][j] = 1;
			}
		}
	}
}
