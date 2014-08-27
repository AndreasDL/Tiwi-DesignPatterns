package ui.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import ui.drawables.Drawable;
import ui.events.EventSourcePanel;

/**
 * @author wijnand.schepens@hogent.be
 */
public class DrawPanel extends EventSourcePanel 
{
	protected List<Drawable>       drawables = new LinkedList<>();
	protected Map<Drawable, Point> positions = new HashMap<>();

	public DrawPanel(int w, int h)
	{
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(w, h));
	}

	// pre: drawable not already added
	public void addDrawable(Drawable drawable, Point location)
	{
		drawables.add(drawable);
		positions.put(drawable, location);
		repaint();
	}

	// callback
	public void changed(Drawable source)
	{
		repaint();
	}

	public Drawable getDrawableAtLocation(Point p)
	{
		for (int i = drawables.size() - 1; i >= 0; i--)
		{
			Drawable dr = drawables.get(i);
			Point pos = positions.get(dr);
			if (dr.contains(new Point(p.x - pos.x, p.y - pos.y)))
				return dr;
		}
		return null;
	}

	public int getDrawableCount()
	{
		return drawables.size();
	}

	public Drawable getLastDrawable()
	{
		if (drawables.isEmpty())
			return null;
		else
			return drawables.get(drawables.size() - 1);
	}

	// pre: drawable added
	public Point getDrawablePosition(Drawable drawable)
	{
		return positions.get(drawable);
	}

	// pre: drawable added
	public void setDrawablePosition(Drawable drawable, Point pos)
	{
		positions.put(drawable, pos);
		repaint();
	}

	public void removeDrawable(Drawable drawable)
	{
		drawables.remove(drawable);
		positions.remove(drawable);
		repaint();
	}

	public void removeLastDrawable()
	{
		if (!drawables.isEmpty())
			removeDrawable(getLastDrawable());
	}

	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (Drawable dr : drawables)
		{
			Point pos = positions.get(dr);
			g2.translate(pos.x, pos.y);
			dr.draw(g2);
			g2.translate(-pos.x, -pos.y);
		}
	}


	
}
