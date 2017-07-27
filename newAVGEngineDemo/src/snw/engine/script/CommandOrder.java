package snw.engine.script;

public enum CommandOrder
{
	COMMAND_SAY, COMMAND_JUMP, COMMAND_SELECT, COMMAND_END, COMMAND_ERROR;

	public static CommandOrder getOrderByName(String name)
	{
		if (name.equals("say"))
		{
			return (COMMAND_SAY);
		} else if (name.equals("jump"))
		{
			return (COMMAND_JUMP);
		} else if (name.equals("select"))
		{
			return (COMMAND_SELECT);
		} else if (name.equals("end"))
		{
			return (COMMAND_END);
		}
		return (COMMAND_ERROR);
	}
}
