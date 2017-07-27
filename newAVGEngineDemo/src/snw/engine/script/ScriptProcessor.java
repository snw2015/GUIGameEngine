package snw.engine.script;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ScriptProcessor
{
	private final AVGScreen screen;

	private int programCounter = 0;

	private String chapterName;
	private ScriptLine[] lines;
	private HashMap<String, Integer> labels = new HashMap<String, Integer>();
	private int lineNum = 0;
	private int labelNum = 0;

	public static final int TYPE_CHAPTER = 0;
	public static final int TYPE_FILE = 1;
	public static final int TYPE_GLOBAL = 2;

	public ScriptProcessor(String chapterName, AVGScreen screen,
			BufferedReader chapterFile)
	{
		this.chapterName = chapterName;
		this.screen = screen;

		String line = null;
		ArrayList<ScriptLine> lineList = new ArrayList<ScriptLine>();
		try
		{
			line = chapterFile.readLine();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (line != null)
		{
			addLineToList(lineList, line);
			try
			{
				line = chapterFile.readLine();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		lines = lineList.toArray(new ScriptLine[0]);
	}

	private void addLineToList(ArrayList<ScriptLine> lineList, String line)
	{
		// TODO Auto-generated method stub
		if (line.startsWith("#"))
		{
			String label = line.substring(1, line.indexOf(' '));
			lineList.add(new ScriptLine(line.substring(line.indexOf(' ') + 1)));
			putLabel(label);
		} else
		{
			lineList.add(new ScriptLine(line));
		}
		lineNum++;
	}

	private void putLabel(String label)
	{
		labels.put(label, lineNum);
		labelNum++;
	}

	public void process()
	{
		ScriptLine line = getLineAt(programCounter++);

		if (line == null)
		{
			// TODO
			System.out.println("run out the lines...");
		} else
		{
			String inputs = line.getInputs();

			switch (line.getOrder())
			{
			case COMMAND_SAY:
				say(inputs);
				break;
			case COMMAND_JUMP:
				jump(inputs);
				break;
			case COMMAND_SELECT:
				select(inputs);
				break;
			case COMMAND_END:
				say("END测试");
				break;
			default:
				// TODO
				System.out.println("No such syntax anyway my f*cking Jesus!");
				break;
			}
		}
	}

	private void select(String inputs)
	{
		// TODO Auto-generated method stub
		String[] splits = inputs.split("\\|");
		screen.displaySelection(splits);
	}

	private void jump(String targetLabel)
	{
		int target = getIndexByLabel(targetLabel);
		if (target >= 0)
		{
			programCounter = target;
			process();
		} else
		{
			// TODO
			System.out.println("syntax error");
		}
	}

	private void say(String content)
	{
		if (content.startsWith("["))
		{
			// TODO "\["
			String[] splits = content.substring(1).split("]", 2);
			screen.say(splits[0], splits[1]);
		} else
		{
			screen.say("", content);
		}
	}

	private ScriptLine getLineAt(int index)
	{
		// TODO Auto-generated method stub
		if (index >= lineNum)
		{
			return (null);
		} else
		{
			return (lines[index]);
		}
	}

	private int getIndexByLabel(String label)
	{
		if (label.contains(label))
		{
			return (labels.get(label));
		} else
		{
			return (-1);
		}
	}

	public String getChapterName()
	{
		return chapterName;
	}

	public int getLabelNum()
	{
		return labelNum;
	}
}
