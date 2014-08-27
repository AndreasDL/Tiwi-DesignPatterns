package commands;

import java.awt.Point;
import ui.draw.DrawPanel;
import ui.drawables.Drawable;

/**
 * @author wijnand.schepens@hogent.be
 */
public class AddDrawableCommand implements Command
{
	private Drawable drawable;
	private Point    position;

	public AddDrawableCommand(Drawable drawable, Point position)
	{
		this.drawable = drawable;
		this.position = position;
	}
	
	
	@Override
	public void execute(DrawPanel drawPanel)
	{
		System.out.println("add");
		drawPanel.addDrawable(drawable, position);
	}

	@Override
	public void unexecute(DrawPanel drawPanel)
	{
		System.out.println("un-add");
		drawPanel.removeDrawable(drawable);
	}

}
