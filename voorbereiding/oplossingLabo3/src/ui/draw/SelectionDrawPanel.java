package ui.draw;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.HashSet;
import java.util.Set;
import ui.drawables.Drawable;

/**
 * @author wijnand.schepens@hogent.be
 */
public class SelectionDrawPanel extends DrawPanel 
{
	private final static Stroke DASH_STROKE = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, new float[]{2f, 2f}, 0.0f); 
	
	private Set<Drawable> selectedDrawables = new HashSet<>();
	
	public SelectionDrawPanel(int w, int h)
	{
		super(w, h);
	}
	
	
	public void addToSelection(Drawable d)
	{
		if (d == null)
			return;
		if (!drawables.contains(d))
			throw new RuntimeException("Can't select drawable which is not added.");
		selectedDrawables.add(d);
		repaint();
	}
	
	public void clearSelection()
	{
		selectedDrawables.clear();
		repaint();
	}
	
	public void select(Drawable d)
	{
		clearSelection();
		addToSelection(d);
	}
	
	public boolean isSelected(Drawable d)
	{
		return selectedDrawables.contains(d);
	}
	
	public Set<Drawable> getSelectedDrawables() 
	{
		return new HashSet(selectedDrawables); // copy!
	}
	
	@Override
	public void removeDrawable(Drawable drawable)
	{
		selectedDrawables.remove(drawable);
		super.removeDrawable(drawable);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.RED);
		g2.setStroke(DASH_STROKE);
		for (Drawable dr : selectedDrawables)
		{
			Point pos = positions.get(dr);
			Dimension size = dr.getSize();
			g2.translate(pos.x, pos.y);
			g2.drawRect(-2, -2, size.width + 4, size.height + 4);
			g2.translate(-pos.x, -pos.y);
		}
	}
}
