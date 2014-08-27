package commands;

import java.awt.Point;
import ui.draw.DrawPanel;
import ui.drawables.Drawable;

/**
 * @author wijnand.schepens@hogent.be
 */
public class MoveCommand implements Command
{
	private Drawable drawable;
	private Point    startPosition;
	private Point    stopPosition;

	public MoveCommand(DrawPanel panel, Drawable drawable, Point stopPosition)
	{
		this.drawable = drawable;
		this.startPosition = panel.getDrawablePosition(drawable);
		this.stopPosition = stopPosition;
	}
	
	@Override
	public void execute(DrawPanel drawPanel)
	{
		System.out.println("move");
		drawPanel.setDrawablePosition(drawable, stopPosition);
	}

	@Override
	public void unexecute(DrawPanel drawPanel)
	{
		System.out.println("un-move");
		drawPanel.setDrawablePosition(drawable, startPosition);
	}

}
