package snw.tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

import snw.engine.script.*;
import snw.file.FileDirectReader;

public class TestForScript implements AVGScreen
{
	private DataSet data;
	private Scanner scanner = new Scanner(System.in);

	public TestForScript()
	{
		data = new DataSet();
		data.initialize();
	}

	@Override
	public void say(String speakerName, String content)
	{
		// TODO Auto-generated method stub
		System.out.println((!speakerName.equals("") ? (speakerName + " says: \"") : ("\""))
				+ content + "\"");
	}

	@Override
	public void displaySelection(String[] contains)
	{
		// TODO Auto-generated method stub
		System.out.println(contains[0]);
		for (int i = 1; i < contains.length; i++)
		{
			System.out.println(String.format("(%d): ", i) + contains[i]);
		}
		int option = -1;
		while (true)
		{
			String answer = scanner.nextLine();
			try
			{
				option = Integer.parseInt(answer);
				break;
			} catch (NumberFormatException e)
			{
				System.out.println("Wrong input.");
			}
		}
		data.setSelectedValue(option);
	}

	@Override
	public void displayGraphic(String imageName, int position)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void displayBGM(String BGMName)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void displaySound(String soundName)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getSelectedValue()
	{
		// TODO Auto-generated method stub
		return (data.getSelectedValue());
	}

	@Override
	public boolean getBooleanVar(int type, int index)
	{
		if (type == ScriptProcessor.TYPE_CHAPTER)
		{
			return (data.getChapterSwitch(index));
		} else if (type == ScriptProcessor.TYPE_FILE)
		{
			return (data.getFileSwitch(index));
		} else if (type == ScriptProcessor.TYPE_GLOBAL)
		{
			return (data.getGlobalSwitch(index));
		}
		return false;
	}

	@Override
	public int getIntVar(int type, int index)
	{
		if (type == ScriptProcessor.TYPE_CHAPTER)
		{
			return (data.getChapterInt(index));
		} else if (type == ScriptProcessor.TYPE_FILE)
		{
			return (data.getFileInt(index));
		} else if (type == ScriptProcessor.TYPE_GLOBAL)
		{
			return (data.getGlobalInt(index));
		}
		return 0;
	}

	@Override
	public String getStringVar(int type, int index)
	{
		if (type == ScriptProcessor.TYPE_CHAPTER)
		{
			return (data.getChapterString(index));
		} else if (type == ScriptProcessor.TYPE_FILE)
		{
			return (data.getFileString(index));
		} else if (type == ScriptProcessor.TYPE_GLOBAL)
		{
			return (data.getGlobalString(index));
		}
		return "";
	}

	public static void main(String[] args)
	{
		TestForScript ts = new TestForScript();

		BufferedReader chapterFile = null;
		try
		{
			chapterFile = FileDirectReader
					.getBufferedReaderOfFile("file/script/test.scp");
		} catch (FileNotFoundException e)
		{
			System.out.println("File doesn't exist.");
			e.printStackTrace();
		}

		ScriptProcessor processor = new ScriptProcessor("test", ts, chapterFile);

		while (!ts.scanner.nextLine().equals("quit"))
		{
			processor.process();
		}
	}
}
