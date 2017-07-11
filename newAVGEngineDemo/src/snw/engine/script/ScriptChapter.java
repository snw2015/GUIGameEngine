package snw.engine.script;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ScriptChapter
{
	private final String name;
	private final ArrayList<ScriptLine> lines = new ArrayList<ScriptLine>();
	private final HashMap<String, Integer> labels = new HashMap<String, Integer>();
	private int lineNum = 0;
	private int labelNum = 0;

	public ScriptChapter(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void addLine(ScriptLine line)
	{
		lines.add(line);
		lineNum++;
	}

	public void addLine(ScriptLine line, String label)
	{
		lines.add(line);
		labels.put(label, lineNum++);
		labelNum++;
	}

	public ScriptLine getLineAt(int index)
	{
		if (lines.size() <= index)
		{
			return (null);
		}

		return (lines.get(index));

	}

	public int getIndexByLabel(String label)
	{
		return (labels.get(label));
	}

	public ScriptLine getLineByLabel(String label)
	{
		return (getLineAt(labels.get(label)));
	}
}
