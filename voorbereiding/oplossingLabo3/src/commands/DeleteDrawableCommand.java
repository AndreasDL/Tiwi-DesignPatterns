package commands;

import java.awt.Point;
import ui.draw.DrawPanel;
import ui.drawables.Drawable;

/**
 * @author wijnand.schepens@hogent.be
 */
public class DeleteDrawableCommand implements Command
{
	private Drawable drawable;
	private Point    position;

	public DeleteDrawableCommand(DrawPanel drawPanel, Drawable drawable)
	{
		this.drawable = drawable;
		this.position = drawPanel.getDrawablePosition(drawable);
	}

	@Override
	public void execute(DrawPanel drawPanel)
	{
		System.out.println("delete");
		drawPanel.removeDrawable(drawable);
	}

	@Override
	public void unexecute(DrawPanel drawPanel)
	{
		System.out.println("un-delete");
		drawPanel.addDrawable(drawable, position);
	}
	
	
}
