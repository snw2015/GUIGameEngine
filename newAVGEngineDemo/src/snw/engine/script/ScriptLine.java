package snw.engine.script;

public class ScriptLine
{
	private final CommandOrder order;
	private final String inputs;

	public ScriptLine(CommandOrder order, String inputs)
	{
		this.order = order;
		this.inputs = inputs;
	}

	public ScriptLine(String inputString)
	{
		int split = inputString.indexOf(' ');
		String orderString = inputString.substring(0, split);
		order = CommandOrder.getOrderByName(orderString);
		inputs = inputString.substring(split + 1);
	}

	public CommandOrder getOrder()
	{
		return order;
	}

	public String getInputs()
	{
		return inputs;
	}
}
