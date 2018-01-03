package snw.engine.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class UserConfig
{
	private static HashMap<String, String> data = new HashMap<String, String>();

	private static final int STATE_NEW = 1;
	private static final int STATE_CHANGE = 2;
	private static final int STATE_FAIL = 0;

	public static int setDatabase(BufferedReader br)
	{
		int state = STATE_FAIL;

		if (br != null)
		{
			String line = "";
			String[] splits = new String[2];
			if (data.isEmpty())
			{
				state = STATE_NEW;
			} else
			{
				data.clear();
				state = STATE_CHANGE;
			}

			while (line != null)
			{
				try
				{
					line = br.readLine();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (line != null)
				{
					splits = line.split("=");
					int setState = setData(splits[0].trim(), splits[1].trim());
					if (setState == STATE_FAIL)
					{
						return (STATE_FAIL);
					}
				}
			}
		}
		return (state);
	}

	public static int setData(String name, String value)
	{
		if (name != null)
		{
			if (data.containsKey(name))
			{
				data.put(name, value);
				return (STATE_CHANGE);
			} else
			{
				data.put(name, value);
				return (STATE_NEW);
			}
		} else
		{
			return (STATE_FAIL);
		}
	}

	public static String getData(String name)
	{
		if (data.containsKey(name))
		{
			return (data.get(name));
		} else
		{
			return ("");
		}
	}

	public static boolean remove(String name)
	{
		if (data.containsKey(name))
		{
			data.remove(name);
			return (true);
		} else
		{
			return (false);
		}
	}

	public static boolean load(String path)
	{
		if (path != null)
		{
			File file = new File(path);
			BufferedReader reader = null;
			try
			{
				reader = new BufferedReader(
						new InputStreamReader(new FileInputStream(file)));
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(file.getAbsolutePath());
				return (false);
			}
			if (setDatabase(reader) == STATE_FAIL)
			{
				return (false);
			}
		}
		return (false);
	}
}
