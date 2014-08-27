package ui.drawables;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

/**
 * @author wijnand.schepens@hogent.be
 */
public class ShapeDrawable implements Drawable
{
	private Shape shape;

	public ShapeDrawable(Shape shape)
	{
		this.shape = shape;
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		g.fill(shape);
	}

	@Override
	public boolean contains(Point p)
	{
		return shape.contains(p);
	}
	
	@Override
	public Dimension getSize()
	{
		return shape.getBounds().getSize();
	}
}
