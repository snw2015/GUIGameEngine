package snw.engine.script;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class ScriptProcessor
{
	private final ScriptChapter scripts;
	private int programCounter = 0;

	public static final int COMMAND_SAY = 0;
	public static final int COMMAND_JUMP = 1;
	public static final int COMMAND_END = 2;

	public ScriptProcessor(String chapterName, DataSet data, BufferedReader chapterFile)
	{
		scripts = new ScriptChapter(chapterName);
		String line = null;
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
			// labeltag
			if (line.startsWith("#"))
			{
				String label = line.substring(1, line.indexOf(' '));
				scripts.addLine(new ScriptLine(line.substring(line.indexOf(' ') + 1),
						getOrderList()), label);

			} else
			{
				scripts.addLine(new ScriptLine(line, getOrderList()));
			}
			try
			{
				line = chapterFile.readLine();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String[] getOrderList()
	{
		// TODO Auto-generated method stub
		return new String[] { "say", "jump", "end" };
	}

	public CommandInfo process()
	{
		CommandInfo ci = new CommandInfo();
		while (true)
		{
			ScriptLine line = scripts.getLineAt(programCounter++);

			if (line == null)
			{
				// TODO
				System.out.println("run out the lines...");
				break;
			} else
			{
				String inputs = line.getInputs();

				switch (line.getOrder())
				{
				case COMMAND_SAY:
					if (inputs.startsWith("["))
					{
						// TODO "\["
						ci.inputs = inputs.substring(1).split("]", 2);
						ci.command = COMMAND_SAY;
					} else
					{
						ci.inputs = new String[] { inputs };
						ci.command = COMMAND_SAY;
					}
					return (ci);
				case COMMAND_JUMP:
					int index = scripts.getIndexByLabel(inputs);
					if (index >= 0)
					{
						programCounter = index;
					} else
					{
						// TODO
						System.out.println("syntax error");
					}
					break;
				case COMMAND_END:
					ci.command = COMMAND_END;
					return (ci);
				default:
					// TODO
					System.out.println("No such syntax anyway my fucking writer!");
					break;
				}
			}
		}
		return (null);
	}
}
