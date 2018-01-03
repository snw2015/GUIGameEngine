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
			// System.out.println(programCounter + " , " + inputs);

			switch (line.getOrder())
			{
			case SAY:
				say(inputs);
				break;
			case JUMP:
				jump(inputs);
				break;
			case SELECT:
				select(inputs);
				break;
			case END:
				end(inputs);
				break;
			case IF:
				ifJump(inputs);
				break;
			default:
				// TODO
				System.out.println("No such syntax anyway my f*cking Jesus!");
				break;
			}
		}
	}

	private void end(String endName)
	{
		// TODO Auto-generated method stub
		screen.end(endName);
	}

	private void ifJump(String inputs)
	{
		if (inputs.charAt(0) == '@')
		{
			ifJumpVar(inputs);
		} else
		{
			ifJumpSelect(inputs);
		}
	}

	private void ifJumpVar(String inputs)
	{
		// TODO Auto-generated method stub

	}

	private void ifJumpSelect(String inputs)
	{
		// TODO Auto-generated method stub
		String[] splits = inputs.split(" ");
		int value = Integer.parseInt(splits[0]);
		if (screen.getSelectedValue() == value)
		{
			jump(splits[1]);
		} else
		{
			process();
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
		String[] splits = content.split("\\|", 2);
		if (splits.length == 2)
		{
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
