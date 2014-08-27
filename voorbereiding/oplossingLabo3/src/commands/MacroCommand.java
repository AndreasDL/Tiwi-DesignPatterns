package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ui.draw.DrawPanel;

/**
 * @author wijnand.schepens@hogent.be
 */
public class MacroCommand implements Command
{
	protected List<Command> commands;

	public MacroCommand(Command... commands)
	{
		this.commands = new ArrayList<>(Arrays.asList(commands));
	}
	
	public void addCommand(Command command)
	{
		commands.add(command);
	}

	
	@Override
	public void execute(DrawPanel drawPanel)
	{
		for (Command cmd: commands)
			cmd.execute(drawPanel);
	}

	@Override
	public void unexecute(DrawPanel drawPanel)
	{
		for (int i = commands.size() - 1; i >= 0; i--)
			commands.get(i).unexecute(drawPanel);
	}
}
