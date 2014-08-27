package ui.drawables;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * @author wijnand.schepens@hogent.be
 */
public class RectangleDrawable implements Drawable
{
	private int w, h;

	public RectangleDrawable(int w, int h)
	{
		this.w = w;
		this.h = h;
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		g.fillRect(0, 0, w, h);
	}

	@Override
	public boolean contains(Point p)
	{
		return p.x >= 0 && p.x <= w && p.y >=0 && p.y <= h;
	}

	public Dimension getSize()
	{
		return new Dimension(w, h);
	}	
}
