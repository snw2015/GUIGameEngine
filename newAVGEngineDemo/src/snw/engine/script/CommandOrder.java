package snw.engine.script;

public enum CommandOrder
{
	SAY, JUMP, SELECT, END, SAVE, IF, ERROR;

	public static CommandOrder getOrderByName(String name)
	{
		if (name.equals("say"))
		{
			return (SAY);
		} else if (name.equals("jump"))
		{
			return (JUMP);
		} else if (name.equals("select"))
		{
			return (SELECT);
		} else if (name.equals("save"))
		{
			return (SAVE);
		} else if (name.equals("if"))
		{
			return (IF);
		} else if (name.equals("end"))
		{
			return (END);
		}
		return (ERROR);
	}
}
