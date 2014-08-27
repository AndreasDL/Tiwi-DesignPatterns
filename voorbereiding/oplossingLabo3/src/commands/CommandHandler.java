package commands;

import java.util.ArrayList;
import java.util.List;
import ui.draw.DrawPanel;

/**
 * @author wijnand.schepens@hogent.be
 */
public class CommandHandler 
{
	private List<Command> list = new ArrayList<>();
	private int           actualSize = 0;
	
	public void addAndExecuteCommand(Command command, DrawPanel drawPanel)
	{
		command.execute(drawPanel);
		chopList();
		list.add(command);
		actualSize = list.size();
	}
	
	public Command getLastCommand()
	{
		if (actualSize == 0)
			return null;
		else
			return list.get(actualSize - 1);
	}
	
	public boolean canUndo()
	{
		return actualSize > 0;
	}
	
	public boolean canRedo()
	{
		return actualSize < list.size();
	}
	
	public void performUndo(DrawPanel drawPanel)
	{
		if (canUndo())
		{
			Command cmd = getLastCommand();
			cmd.unexecute(drawPanel);
			actualSize--;
		}
	}
	
	public void performRedo(DrawPanel drawPanel)
	{
		if (canRedo())
		{
			Command cmd = list.get(actualSize);
			cmd.execute(drawPanel);
			actualSize++;
		}
	}

	private void chopList()
	{
		if (actualSize < list.size())
			list = list.subList(0, actualSize);
	}
}
