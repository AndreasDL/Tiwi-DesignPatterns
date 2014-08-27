package ui.drawables;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import ui.draw.SelectionDrawPanel;

/**
 * @author wijnand.schepens@hogent.be
 */
public interface Drawable 
{
	void draw(Graphics2D g);
	
	boolean contains(Point p);

	Dimension getSize();
}
