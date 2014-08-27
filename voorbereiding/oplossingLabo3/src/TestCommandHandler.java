
import commands.Command;
import commands.CommandHandler;
import java.util.Scanner;
import ui.draw.DrawPanel;


/**
 * @author wijnand.schepens@hogent.be
 */
public class TestCommandHandler 
{
	public static void main(String[] args)
	{
		CommandHandler handler = new CommandHandler();
		DrawPanel p = new DrawPanel(10, 10); // dummy
		
		Scanner in = new Scanner(System.in);
		String s = in.next();
		while (true)
		{
			if (s.equals("undo"))
				handler.performUndo(p);
			else if (s.equals("redo"))
				handler.performRedo(p);
			else
				handler.addAndExecuteCommand(new TestCommand(s), p);
			
			System.out.println("can undo? " + handler.canUndo());
			System.out.println("can redo? " + handler.canRedo());
			
			s = in.next();
		}
	}
}
class TestCommand implements Command
{
	private String tag;

	public TestCommand(String tag)
	{
		this.tag = tag;
	}
	
	@Override
	public void execute(DrawPanel drawPanel)
	{
		System.out.println("do " + tag);
	}

	@Override
	public void unexecute(DrawPanel drawPanel)
	{
		System.out.println("undo " + tag);
	}
}