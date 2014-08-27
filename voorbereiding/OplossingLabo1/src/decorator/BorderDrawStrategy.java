/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package decorator;

import common.ICreature;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import strategy.IDrawStrategy;

/**
 *
 * @author Wijnand
 */
public class BorderDrawStrategy implements IDrawStrategy
{
	private IDrawStrategy base;

	public BorderDrawStrategy(IDrawStrategy base)
	{
		this.base = base;
	}

	
	@Override
	public void draw(ICreature creature, Graphics2D g, int maxx, int maxy)
	{
		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke(4.0f));
		g.drawRoundRect(0, 0, maxx - 1, maxy - 1, 20, 20);
		g.setStroke(oldStroke);
		
		g.translate(10, 10);
		base.draw(creature, g, maxx - 20, maxy - 20);
	}
	
}
