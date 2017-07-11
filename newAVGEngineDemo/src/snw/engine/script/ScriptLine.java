package snw.engine.script;

public class ScriptLine
{
	private final int order;
	private final String inputs;

	public ScriptLine(int order, String inputs)
	{
		this.order = order;
		this.inputs = inputs;
	}

	public ScriptLine(String inputString, String[] orderList)
	{
		int split = inputString.indexOf(' ');
		String orderString = inputString.substring(0, split);
		int i = 0;
		while (i < orderList.length && !orderString.equals(orderList[i]))
		{
			i++;
		}
		if (i < orderList.length)
		{
			order = i;
		} else
		{
			// TODO
			order = -1;
		}
		inputs = inputString.substring(split + 1);
	}

	public int getOrder()
	{
		return order;
	}

	public String getInputs()
	{
		return inputs;
	}
}
