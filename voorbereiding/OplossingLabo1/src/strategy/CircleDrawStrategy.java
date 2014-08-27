/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import common.ICreature;
import java.awt.Graphics2D;

/**
 *
 * @author Wijnand
 */
public class CircleDrawStrategy implements IDrawStrategy
{

	@Override
	public void draw(ICreature creature, Graphics2D g, int maxx, int maxy)
	{
		
		g.drawOval(0, 0, maxx - 1, maxy - 1);
	}
	
}
